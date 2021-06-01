package com.aemiralfath.decare.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity
import com.aemiralfath.decare.data.source.local.entity.YogaEntity
import com.aemiralfath.decare.data.source.local.room.DecareDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val decareDao: DecareDao) {
    fun getYoga(): Flow<List<YogaEntity>> = decareDao.getYoga()

    fun getArticle(): Flow<List<ArticleEntity>> = decareDao.getArticle()

    fun getReminder(query: SupportSQLiteQuery): Flow<List<ReminderEntity>> =
        decareDao.getAllReminder(query)

    suspend fun insertArticle(listArticle: List<ArticleEntity>) =
        decareDao.insertArticle(listArticle)

    suspend fun insertYoga(listYoga: List<YogaEntity>) = decareDao.insertYoga(listYoga)

    suspend fun insertReminder(reminder: ReminderEntity) =
        decareDao.insertReminder(reminder)

    suspend fun updateReminder(reminder: ReminderEntity) = decareDao.updateReminder(reminder)

    suspend fun deleteReminder(reminder: ReminderEntity) = decareDao.deleteReminder(reminder)
}