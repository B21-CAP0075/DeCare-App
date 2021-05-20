package com.aemiralfath.decare.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.decare.data.DecareRepository

class ArticleViewModel(private val decareRepository: DecareRepository) : ViewModel() {
    val article = decareRepository.getArticle().asLiveData()
}