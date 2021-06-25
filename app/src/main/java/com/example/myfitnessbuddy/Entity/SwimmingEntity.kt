package com.example.myfitnessbuddy.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "swimming")
data class SwimmingEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Long,

    @ColumnInfo(name = "user_id")
    var user_id:Long,

    @ColumnInfo(name  = "date")
    var date : String,

    @ColumnInfo(name = "speed")
    var speed : Float,

    @ColumnInfo(name="kicks")
    var kicks : Int,

    @ColumnInfo(name = "time")
    var time : Float
)