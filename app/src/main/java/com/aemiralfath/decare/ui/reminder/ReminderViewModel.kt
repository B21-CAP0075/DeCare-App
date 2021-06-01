package com.aemiralfath.decare.ui.reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aemiralfath.decare.data.DecareRepository
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity

class ReminderViewModel(decareRepository: DecareRepository) : ViewModel() {

    private val repository = decareRepository

    fun getReminder(sort: String): LiveData<List<ReminderEntity>> = repository.getReminder(sort)

    fun insertReminder(reminderEntity: ReminderEntity) = repository.insertReminder(reminderEntity)

    fun updateReminder(reminderEntity: ReminderEntity) = repository.updateReminder(reminderEntity)

    fun deleteReminder(reminderEntity: ReminderEntity) = repository.deleteReminder(reminderEntity)

}