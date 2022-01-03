package com.example.mycontanct.datamodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "contact_table")
data class Contact(
    @ColumnInfo(name = "contact_name")
    var name: String?,

    @ColumnInfo(name = "contact_number")
    var number: String?,

    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Parcelable

