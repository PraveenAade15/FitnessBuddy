package com.example.myfitnessbuddy.Dao

import androidx.room.*
import com.example.myfitnessbuddy.Entity.SwimmingEntity

@Dao

interface SwimmingDao {
    @Query("SELECT * FROM swimming WHERE user_id=:userId")
    fun getAll(userId:Long) : List<SwimmingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (exercise: SwimmingEntity)

    @Query("SELECT * FROM swimming WHERE id=:logId")
    fun get(logId: Long) : SwimmingEntity

    @Query("DELETE FROM swimming WHERE id=:logId")
    fun delete(logId : Long)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(exercise: SwimmingEntity)
}