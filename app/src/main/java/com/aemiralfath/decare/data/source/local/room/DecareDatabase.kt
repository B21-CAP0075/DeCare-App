package com.aemiralfath.decare.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DecareDatabase : RoomDatabase() {
    abstract fun decareDao() : DecareDao
}