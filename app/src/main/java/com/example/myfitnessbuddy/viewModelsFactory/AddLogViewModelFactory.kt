package com.example.myfitnessbuddy.viewModelsFactory



import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfitnessbuddy.Dao.RunningDao
import com.example.myfitnessbuddy.Dao.SwimmingDao
import com.example.myfitnessbuddy.Dao.WeightLiftingDao
import com.example.myfitnessbuddy.viewModels.AddLogViewModel


/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the SleepDatabaseDao and context to the ViewModel.
 */
class AddLogViewModelFactory(
    private val weightLiftingDao: WeightLiftingDao,
    private val runningDao : RunningDao,
    private val swimmingDao : SwimmingDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddLogViewModel::class.java)) {
            return AddLogViewModel(weightLiftingDao,runningDao,swimmingDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

