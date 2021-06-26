package com.example.myfitnessbuddy.viewModelsfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfitnessbuddy.Dao.LoginDao
import com.example.myfitnessbuddy.viewModels.AddLogViewModel
import com.example.myfitnessbuddy.viewModels.LoginViewModel

class AddLoginViewModelFactory(
    private val loginDao : LoginDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddLogViewModel::class.java)) {
            return LoginViewModel(loginDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}