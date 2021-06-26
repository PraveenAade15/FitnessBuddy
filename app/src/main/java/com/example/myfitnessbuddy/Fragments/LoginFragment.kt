package com.example.myfitnessbuddy.Fragments

// Nishit Amin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.login_screen.*
//import org.rohan.patel.finalprojectandroid.R
//import org.rohan.patel.finalprojectandroid.databinding.LoginScreenBinding
import project.st991497190.vishvakumar.Database.FitDatabase
import project.st991497190.vishvakumar.Entity.LoginEntity
import project.st991497190.vishvakumar.R
import project.st991497190.vishvakumar.UserObject
import project.st991497190.vishvakumar.databinding.LoginScreenBinding

class LoginFragment: Fragment() {

    companion object{
        private lateinit var user : LoginEntity

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<LoginScreenBinding>(inflater,
                R.layout.login_screen,container,false)


        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navView)
        val menu = navigationView.menu
        val target: MenuItem = menu.findItem(R.id.signUpFragment)
        target.setVisible(true)
        val target2: MenuItem = menu.findItem(R.id.loginFragment)
        target2.setVisible(true)
        val target3: MenuItem = menu.findItem(R.id.homeScreenFragment)
        target3.setVisible(false)
        val target4: MenuItem = menu.findItem(R.id.bmiFragment)
        target4.setVisible(false)
        val target5: MenuItem = menu.findItem(R.id.addLogFragment)
        target5.setVisible(false)
        val target6: MenuItem = menu.findItem(R.id.viewLogsFragment)
        target6.setVisible(false)
        val target7: MenuItem = menu.findItem(R.id.articleFragment)
        target7.setVisible(false)

        val application = requireNotNull(this.activity).application
        val loginDao = FitDatabase.getInstance(application).loginDao()

        binding.buttonLogin.setOnClickListener {
            if(binding.editTextEmail.text.trim().isEmpty()) {
                Toast.makeText(activity, "Enter your Email Address!", Toast.LENGTH_SHORT).show()
            }else if(binding.editTextPassword.text.trim().isEmpty()){
                Toast.makeText(activity, "Enter your PASSWORD!", Toast.LENGTH_SHORT).show()
            }else{
                val user = loginDao.checkUser(editTextEmail.text.toString(), editTextPassword.text.toString())
                if(user==null){
                    Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }else{
                    UserObject.user = user
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_appFragment)
                }
            }
            //view?.findNavController()?.navigate(R.id.action_loginFragment_to_appFragment)
        }

        return binding.root
    }
}