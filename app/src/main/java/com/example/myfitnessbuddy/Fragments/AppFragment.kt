package com.example.myfitnessbuddy.Fragments


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myfitnessbuddy.R
import com.example.myfitnessbuddy.databinding.AppScreenBinding
import com.google.android.material.navigation.NavigationView

class AppFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<AppScreenBinding>(
            inflater,
            R.layout.app_screen, container, false
        )

        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navView)
        val menu = navigationView.menu
        val target: MenuItem = menu.findItem(R.id.signUpFragment)
        target.setVisible(false)
        val target2: MenuItem = menu.findItem(R.id.loginFragment)
        target2.setVisible(false)
        val target3: MenuItem = menu.findItem(R.id.homeScreenFragment)
        target3.setVisible(true)
        val target4: MenuItem = menu.findItem(R.id.bmiFragment)
        target4.setVisible(true)
        val target5: MenuItem = menu.findItem(R.id.addLogFragment)
        target5.setVisible(true)
        val target6: MenuItem = menu.findItem(R.id.viewLogsFragment)
        target6.setVisible(true)
        val target7: MenuItem = menu.findItem(R.id.articleFragment)
        target7.setVisible(true)

        binding.buttonCalculateBMI.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_appFragment_to_bmiFragment)
        }

        binding.buttonAddLog.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_appFragment_to_addLogFragment)
        }

        binding.buttonViewLogs.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_appFragment_to_viewLogsFragment)
        }

        binding.buttonArticle?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_appFragment_to_articleFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!, requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}