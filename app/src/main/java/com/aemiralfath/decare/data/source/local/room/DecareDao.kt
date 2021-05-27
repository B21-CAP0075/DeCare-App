package com.aemiralfath.decare.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity
import com.aemiralfath.decare.data.source.local.entity.YogaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DecareDao {

    @Query("SELECT * FROM ArticleEntity")
    fun getArticle(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM YogaEntity")
    fun getYoga(): Flow<List<YogaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(listArticle: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertYoga(listYoga: List<YogaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity)

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @RawQuery(observedEntities = [ReminderEntity::class])
    fun getAllReminder(query: SupportSQLiteQuery): Flow<List<ReminderEntity>>

}