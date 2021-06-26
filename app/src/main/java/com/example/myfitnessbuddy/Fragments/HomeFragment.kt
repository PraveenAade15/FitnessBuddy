package com.example.myfitnessbuddy.Fragments

// Nishit Amin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import project.st991497190.vishvakumar.R
import project.st991497190.vishvakumar.databinding.HomeScreenBinding

class HomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<HomeScreenBinding>(
            inflater,
            R.layout.home_screen, container, false
        )

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

        binding.buttonGetStarted.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeScreenFragment_to_signUpFragment)
        }

        binding.buttonGoToLogin.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeScreenFragment_to_loginFragment)
        }

        return binding.root
    }


}