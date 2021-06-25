package com.example.myfitnessbuddy.Dao

import androidx.room.*
import com.example.myfitnessbuddy.Entity.LoginEntity
import com.example.myfitnessbuddy.Entity.WeightLiftingEntity
import com.example.myfitnessbuddy.UserObject

@Dao


interface WeightLiftingDao {
    private val useObj: LoginEntity
        get() = UserObject.user

    @Query("SELECT * FROM weightlifting WHERE user_id=:userId")
    fun getAll(userId:Long) : List<WeightLiftingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (exercise: WeightLiftingEntity)

    @Query("DELETE FROM weightlifting WHERE id=:logId")
    fun delete(logId : Long)

    @Query("SELECT * FROM weightlifting WHERE id=:logId")
    fun get(logId: Long) : WeightLiftingEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(exercise: WeightLiftingEntity)
}