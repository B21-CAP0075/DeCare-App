package com.aemiralfath.decare.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.decare.data.DecareRepository

class YogaViewModel(decareRepository: DecareRepository) : ViewModel() {
    val yogas = decareRepository.getYoga().asLiveData()
}