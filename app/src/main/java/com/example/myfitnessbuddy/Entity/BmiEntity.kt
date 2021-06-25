package com.example.myfitnessbuddy.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "bmi")
data class BmiEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "weight")
    var weight: Int,

    @ColumnInfo(name = "height")
    var height: Int,

    @ColumnInfo(name = "value")
    var value: Int

)