package com.example.myfitnessbuddy.viewModelsFactory
// Rohan Patel - 991496523
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import project.st991497190.vishvakumar.Dao.RunningDao
import project.st991497190.vishvakumar.Dao.SwimmingDao
import project.st991497190.vishvakumar.Dao.WeightLiftingDao
import project.st991497190.vishvakumar.viewModels.ViewLogsViewModel

class ViewLogViewModelFactory (
    private val weightLiftingDao: WeightLiftingDao,
    private val runningDao : RunningDao,
    private val swimmingDao : SwimmingDao,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewLogsViewModel::class.java)) {
                return ViewLogsViewModel(weightLiftingDao,runningDao,swimmingDao, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}