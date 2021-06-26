package com.example.myfitnessbuddy.Fragments

// Nishit Amin
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.register_screen.*
import project.st991497190.vishvakumar.Entity.LoginEntity
//import org.rohan.patel.finalprojectandroid.R
//import org.rohan.patel.finalprojectandroid.databinding.RegisterScreenBinding
import project.st991497190.vishvakumar.Database.FitDatabase
import project.st991497190.vishvakumar.R
import project.st991497190.vishvakumar.databinding.RegisterScreenBinding

class SignUpFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<RegisterScreenBinding>(inflater,
            R.layout.register_screen,container,false)

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

        binding.btnRegister.setOnClickListener {
            if(binding.editTextName.text.trim().isEmpty()) {
                Toast.makeText(activity, "Enter your full name!", Toast.LENGTH_SHORT).show()
            }else if (binding.editTextEmail.text.trim().isEmpty()){
                Toast.makeText(activity, "Enter your Email Addres!", Toast.LENGTH_SHORT).show()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString()).matches()){
                Toast.makeText(activity, "Enter valid email address!", Toast.LENGTH_SHORT).show()
            }else if (loginDao.exists(binding.editTextEmail.text.trim().toString()) == true){
                Toast.makeText(activity, "Email is already registered!", Toast.LENGTH_SHORT).show()
            }else if (binding.editTextPassword.text.trim().isEmpty()) {
                Toast.makeText(activity, "Enter the PASSWORD!!", Toast.LENGTH_SHORT).show()
            }else if (binding.checkBoxTerms.isChecked!=true) {
                Toast.makeText(activity, "Accept our terms and conditions!!", Toast.LENGTH_SHORT).show()
            }else{
                val newUser = LoginEntity(0, editTextName.text.toString(), editTextEmail.text.toString(), editTextPassword.text.toString())
                loginDao.userRegistration(newUser)
                Toast.makeText(activity, "Registered!", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(R.id.action_signUpFragment_to_homeScreenFragment)
            }
        }
        return binding.root
    }
}