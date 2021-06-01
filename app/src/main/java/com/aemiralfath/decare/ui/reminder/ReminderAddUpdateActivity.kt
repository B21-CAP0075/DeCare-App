package com.aemiralfath.decare.ui.reminder

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity
import com.aemiralfath.decare.databinding.ActivityReminderAddUpdateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ReminderAddUpdateActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    companion object {
        const val EXTRA_REMINDER = "extra_reminder"
        const val EXTRA_POSITION = "extra_position"
        const val RESULT_ADD = 101
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
        const val OLAHRAGA = "Olahraga"
        const val OBAT = "Obat"
        const val ARTIKEL = "Artikel"

        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"

        private val TYPE = arrayListOf(OLAHRAGA, OBAT, ARTIKEL)

        private val ALARM =
            arrayListOf("ONCE", "REPEAT")
    }

    private var position = 0
    private var isEdit = false
    private var reminder: ReminderEntity? = null

    private val viewModel: ReminderViewModel by viewModel()
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var binding: ActivityReminderAddUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        alarmReceiver = AlarmReceiver()
        populateForm()

        reminder = intent.getParcelableExtra(EXTRA_REMINDER)
        if (reminder != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            reminder = ReminderEntity()
        }

        if (isEdit) {
            binding.tvReminderTitle.text = getString(R.string.change)
            binding.btnSubmit.text = getString(R.string.update)
            if (reminder != null) {
                reminder?.let {
                    binding.edtTitle.setText(it.title)
                    binding.edtDescription.setText(it.description)
                    binding.spinnerAlarm.setText(it.reminder)
                    binding.spinnerType.setText(TYPE[it.type])
                    binding.tvTime.text = it.time
                    binding.switchReminder.isChecked = it.status
                }
            }
        } else {
            binding.tvReminderTitle.text = getString(R.string.add)
            binding.btnSubmit.text = getString(R.string.save)
        }

        binding.btnSubmit.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()
            val type = binding.spinnerType.text.toString().trim()
            val alarm = binding.spinnerAlarm.text.toString().trim()
            val time = binding.tvTime.text.toString().trim()
            val status = binding.switchReminder.isChecked

            when {
                title.isEmpty() -> binding.edtTitle.error = getString(R.string.empty)
                description.isEmpty() -> binding.edtDescription.error = getString(R.string.empty)
                type.isEmpty() -> binding.spinnerType.error = getString(R.string.empty)
                alarm.isEmpty() -> binding.spinnerAlarm.error = getString(R.string.empty)
                alarmReceiver.isDateInvalid(
                    time,
                    AlarmReceiver.TIME_FORMAT
                ) -> binding.tvTime.error =
                    getString(R.string.empty)
                else -> {
                    reminder.let {
                        it?.title = title
                        it?.description = description
                        it?.type = checkType(type)
                        it?.reminder = alarm
                        it?.time = time
                        it?.status = status
                    }

                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)

                    if (isEdit) {
                        intent.putExtra(EXTRA_REMINDER, reminder)
                        viewModel.updateReminder(reminder as ReminderEntity)
                        reminderStatus(reminder as ReminderEntity)
                        setResult(RESULT_UPDATE, intent)
                        finish()
                    } else {
                        val alarmID = createAlarmID()
                        reminder?.alarmID = alarmID
                        intent.putExtra(EXTRA_REMINDER, reminder)
                        viewModel.insertReminder(reminder as ReminderEntity)
                        reminderStatus(reminder as ReminderEntity)
                        setResult(RESULT_ADD, intent)
                        finish()
                    }
                }
            }
        }
    }

    private fun reminderStatus(reminder: ReminderEntity) {
        if (reminder.status) {
            if (reminder.reminder == ALARM[0]) {
                alarmReceiver.setOneTimeAlarm(
                    this,
                    TYPE[reminder.type],
                    reminder.time.toString(),
                    reminder.description.toString(),
                    reminder.title.toString(),
                    reminder.alarmID ?: 0
                )
            } else {
                alarmReceiver.setRepeatingAlarm(
                    this,
                    TYPE[reminder.type],
                    reminder.time.toString(),
                    reminder.description.toString(),
                    reminder.title.toString(),
                    reminder.alarmID ?: 0
                )
            }
        } else {
            alarmReceiver.cancelAlarm(this, reminder.alarmID ?: 0)
        }
    }

    private fun populateForm() {

        binding.spinnerType.setAdapter(
            ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                TYPE
            )
        )
        binding.spinnerType.inputType = InputType.TYPE_NULL

        binding.spinnerAlarm.setAdapter(
            ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                ALARM
            )
        )
        binding.spinnerAlarm.inputType = InputType.TYPE_NULL

        binding.btnTime.setOnClickListener {
            val timePickerFragmentOne = TimePickerFragment()
            if (binding.spinnerType.text.toString() == ALARM[0]) {
                timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            } else {
                timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }
        }
    }

    private fun checkType(data: String): Int {
        return TYPE.indexOf(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form_reminder, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    val data = reminder as ReminderEntity
                    viewModel.deleteReminder(data)
                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    alarmReceiver.cancelAlarm(
                        this@ReminderAddUpdateActivity,
                        data.alarmID ?: 0
                    )
                    setResult(RESULT_DELETE, intent)
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDialogTimeSet(hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        binding.tvTime.text = dateFormat.format(calendar.time)
    }

    @Throws(Exception::class)
    fun createAlarmID(): Int {
        val str = UUID.randomUUID().toString()
        val uid = str.hashCode().toString()
        return uid.replace("-".toRegex(), "").toInt()
    }

}