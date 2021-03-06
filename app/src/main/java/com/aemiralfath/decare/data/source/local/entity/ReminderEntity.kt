package com.aemiralfath.decare.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "time")
    var time: String? = null,

    @ColumnInfo(name = "reminder")
    var reminder: String? = null,

    @ColumnInfo(name = "alarmID")
    var alarmID: Int? = 0,

    @ColumnInfo(name = "type")
    var type: Int = 0,

    @ColumnInfo(name = "status")
    var status: Boolean = false,
) : Parcelable
