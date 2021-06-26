package com.example.myfitnessbuddy.Fragments

// Vishvakumar Mavani
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.bmi_screen.*
//import org.rohan.patel.finalprojectandroid.R
//import org.rohan.patel.finalprojectandroid.databinding.BmiScreenBinding
import project.st991497190.vishvakumar.R
import project.st991497190.vishvakumar.databinding.BmiScreenBinding

class BmiFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding = DataBindingUtil.inflate<BmiScreenBinding>(inflater,
                R.layout.bmi_screen,container,false)

        binding.buttonCalculate.setOnClickListener {
            if(binding.inputWeight.text.isNotEmpty() && binding.inputHeight.text.isNotEmpty()){
                var bmi = 0.0
                val weight = inputWeight.text.toString().toFloat()
                val height = inputHeight.text.toString().toFloat()

                bmi = ((weight/height/height * 10000).toDouble())

                if(bmi<18.5){
                    textBmi.text = "Your BMI: %.2f".format(bmi)
                    textHealth.text = "Underweight"
                }else if(bmi in 18.5..24.9){
                    textBmi.text = "Your BMI: %.2f".format(bmi)
                    textHealth.text = "Normal Weight"
                }else if(bmi in 25.0..29.9){
                    textBmi.text = "Your BMI: %.2f".format(bmi)
                    textHealth.text = "Overweight"
                }else if(bmi > 30){
                    textBmi.text = "Your BMI: %.2f".format(bmi)
                    textHealth.text = "Obesity"
                }

                textResult.setText(R.string.result_textview)

            }else{
                Toast.makeText(activity, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
        
    }
}