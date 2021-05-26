package com.aemiralfath.decare.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.decare.data.DecareRepository

class YogaViewModel(private val decareRepository: DecareRepository) : ViewModel() {
    val yogas = decareRepository.getYoga().asLiveData()
}