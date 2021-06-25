package com.example.myfitnessbuddy.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfitnessbuddy.Entity.BmiEntity

@Dao
interface BmiDao {
    @Query("SELECT * FROM bmi")
    fun getAll(): List<BmiEntity>

    @Query("DELETE FROM bmi")
    fun deleteAllBmi()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bmi: BmiEntity)

}