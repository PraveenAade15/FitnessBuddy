package com.example.myfitnessbuddy.viewModels

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.myfitnessbuddy.Dao.LoginDao
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginViewModel(val loginDao: LoginDao, application: Application) : ViewModel() {

    var loginViewModel = LoginViewModel(loginDao, application)

    var _name: String? = ""
    var _email: String? = ""
    var _password: String? = ""

    var name: String?
        get() {
            return _name
        }
        set(value) {
            _name = value
        }

    var email: String?
        get() {
            return _email
        }
        set(value) {
            _email = value
        }

    var password: String?
        get() {
            return _password
        }
        set(value) {
            _password = value
        }
}