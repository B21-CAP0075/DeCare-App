package com.aemiralfath.decare.data.source.local

import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.local.entity.YogaEntity
import com.aemiralfath.decare.data.source.local.room.DecareDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val decareDao: DecareDao) {
    fun getYoga(): Flow<List<YogaEntity>> = decareDao.getYoga()
    fun getArticle(): Flow<List<ArticleEntity>> = decareDao.getArticle()
    suspend fun insertArticle(listArticle: List<ArticleEntity>) = decareDao.insertArticle(listArticle)
    suspend fun insertYoga(listYoga: List<YogaEntity>) = decareDao.insertYoga(listYoga)
}