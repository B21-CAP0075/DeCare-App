package com.aemiralfath.decare.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    val banner: String,
    val date: String,
    @PrimaryKey
    val id: Int,
    val link: String,
    val title: String
)