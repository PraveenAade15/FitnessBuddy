package com.example.myfitnessbuddy.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weightlifting")
data class WeightLiftingEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long,

    @ColumnInfo(name = "user_id")
    var user_id:Long,

    @ColumnInfo(name  = "date")
    var date : String,

    @ColumnInfo(name = "reps")
    var reps : Int,

    @ColumnInfo(name="sets")
    var sets : Int,

    @ColumnInfo(name = "weight")
    var weight : Int
)