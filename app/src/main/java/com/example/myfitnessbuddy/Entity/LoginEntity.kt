package com.example.myfitnessbuddy.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
class LoginEntity (

    @PrimaryKey(autoGenerate = true)
    var id : Long,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name="password")
    var password : String
)