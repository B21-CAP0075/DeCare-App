package com.aemiralfath.decare.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class YogaEntity(
    val deskripsi: String,
    val gambar: String,
    @PrimaryKey
    val id: Int,
    val namaPose: String
)