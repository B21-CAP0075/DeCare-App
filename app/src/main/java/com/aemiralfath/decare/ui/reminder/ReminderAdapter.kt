package com.aemiralfath.decare.ui.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity
import com.aemiralfath.decare.databinding.ItemReminderBinding
import com.aemiralfath.decare.util.ReminderDiffCallback
import com.bumptech.glide.Glide

class ReminderAdapter : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val listReminder = ArrayList<ReminderEntity>()
    fun setListReminder(listReminder: List<ReminderEntity>) {
        val diffCallback = ReminderDiffCallback(this.listReminder, listReminder)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listReminder.clear()
        this.listReminder.addAll(listReminder)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val binding =
            ItemReminderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(listReminder[position], position)
    }

    override fun getItemCount(): Int {
        return listReminder.size
    }

    inner class ReminderViewHolder(private val binding: ItemReminderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: ReminderEntity, position: Int) {
            with(binding) {
                tvItemTitle.text = reminder.title
                tvItemDescription.text = reminder.description
                tvItemTime.text = reminder.time
                tvItemAlarm.text = reminder.reminder
                tvItemStatus.text = if (reminder.status) "ON" else "OFF"

                val itemImage = when (reminder.type) {
                    0 -> R.drawable.ic_run
                    1 -> R.drawable.ic_medication
                    2 -> R.drawable.ic_article
                    else -> R.drawable.ic_error
                }

                Glide.with(itemView.context).load(itemImage).into(imgItemReminder)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(reminder, position)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ReminderEntity, position: Int)
    }

}