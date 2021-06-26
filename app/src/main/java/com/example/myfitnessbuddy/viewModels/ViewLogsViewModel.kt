package com.example.myfitnessbuddy.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.myfitnessbuddy.Dao.RunningDao
import com.example.myfitnessbuddy.Dao.SwimmingDao
import com.example.myfitnessbuddy.Dao.WeightLiftingDao

class ViewLogsViewModel(val weightLiftingDao: WeightLiftingDao, val runningDao: RunningDao, val swimmingDao: SwimmingDao,
                        application: Application
) : ViewModel() {

}