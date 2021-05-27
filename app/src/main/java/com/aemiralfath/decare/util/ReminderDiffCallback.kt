package com.aemiralfath.decare.util

import androidx.recyclerview.widget.DiffUtil
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity

class ReminderDiffCallback(
    private val oldList: List<ReminderEntity>,
    private val newList: List<ReminderEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldList[oldItemPosition]
        val newNote = newList[newItemPosition]
        return oldNote.title == newNote.title &&
                oldNote.description == newNote.description &&
                oldNote.date == newNote.date &&
                oldNote.reminder == newNote.reminder &&
                oldNote.status == newNote.status &&
                oldNote.type == newNote.type
    }
}