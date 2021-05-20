package com.aemiralfath.decare.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DecareDao {

    @Query("SELECT * FROM ArticleEntity")
    fun getArticle(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(listArticle: List<ArticleEntity>)

}