package com.example.myfitnessbuddy.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfitnessbuddy.Dao.*
import com.example.myfitnessbuddy.Entity.*


@Database(entities = [BmiEntity::class, RunningEntity::class, WeightLiftingEntity::class, SwimmingEntity::class, LoginEntity::class],version = 4, exportSchema = false)
abstract class FitDatabase : RoomDatabase(){
    abstract fun runningDao(): RunningDao
    abstract fun weightLiftingDao(): WeightLiftingDao
    abstract fun swimmingDao(): SwimmingDao
    abstract fun loginDao(): LoginDao
    abstract fun bmiDao(): BmiDao

    companion object{
        @Volatile
        private var INSTANCE : FitDatabase? = null

        fun getInstance(context: Context) : FitDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    FitDatabase::class.java,
                    "fitappdatabase"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return INSTANCE as FitDatabase
        }
    }
}