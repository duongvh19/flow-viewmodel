package com.duongvh19.plantsapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "plants")
@Parcelize
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId : String,
    val name : String,
    val description : String,
    val growZoneNumber : Int,
    val wateringInterval : Int = 7,
    val imageUrl : String = ""
) : Parcelable
