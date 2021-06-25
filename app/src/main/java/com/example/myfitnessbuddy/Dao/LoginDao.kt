package com.example.myfitnessbuddy.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfitnessbuddy.Entity.LoginEntity

@Dao

interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userRegistration (login: LoginEntity)

    @Query("SELECT * FROM login WHERE email=:email AND password=:password")
    fun checkUser (email: String, password: String) : LoginEntity

    @Query("SELECT EXISTS (SELECT * FROM login WHERE email=:email)")
    fun exists(email: String): Boolean
}