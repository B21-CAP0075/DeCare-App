package com.aemiralfath.decare.ui.reminder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity
import com.aemiralfath.decare.databinding.ActivityReminderBinding
import com.aemiralfath.decare.util.SortUtils
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReminderActivity : AppCompatActivity() {

    private lateinit var addUpdateResult: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityReminderBinding
    private lateinit var adapter: ReminderAdapter
    private val viewModel: ReminderViewModel by viewModel()

    private val reminderObserver = Observer<List<ReminderEntity>> {
        if (it != null) {
            adapter.setListReminder(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getReminder(SortUtils.NEWEST).observe(this, reminderObserver)

        adapter = ReminderAdapter()
        adapter.setOnItemClickCallback(object: ReminderAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ReminderEntity, position: Int) {
                val intent = Intent(this@ReminderActivity, ReminderAddUpdateActivity::class.java)
                intent.putExtra(ReminderAddUpdateActivity.EXTRA_POSITION, position)
                intent.putExtra(ReminderAddUpdateActivity.EXTRA_REMINDER, data)
                addUpdateResult.launch(intent)
            }
        })



        binding.rvReminder.layoutManager = LinearLayoutManager(this)
        binding.rvReminder.setHasFixedSize(true)
        binding.rvReminder.adapter = adapter

        binding.fabAdd.setOnClickListener {
            if (it.id == R.id.fab_add) {
                addUpdateResult.launch(
                    Intent(
                        this@ReminderActivity,
                        ReminderAddUpdateActivity::class.java
                    )
                )
            }
        }

        addUpdateResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.data != null) {
                    if (result.resultCode == ReminderAddUpdateActivity.RESULT_ADD) {
                        showSnackbarMessage(getString(R.string.added))
                    }
                    if (result.resultCode == ReminderAddUpdateActivity.RESULT_UPDATE) {
                        showSnackbarMessage(getString(R.string.changed))
                    } else if (result.resultCode == ReminderAddUpdateActivity.RESULT_DELETE) {
                        showSnackbarMessage(getString(R.string.deleted))
                    }
                }
            }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.root as View, message, Snackbar.LENGTH_SHORT).show()
    }
}