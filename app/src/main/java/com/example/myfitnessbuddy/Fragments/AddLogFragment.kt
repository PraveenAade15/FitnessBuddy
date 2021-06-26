package com.example.myfitnessbuddy.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myfitnessbuddy.Database.FitDatabase
import com.example.myfitnessbuddy.Entity.RunningEntity
import com.example.myfitnessbuddy.Entity.SwimmingEntity
import com.example.myfitnessbuddy.Entity.WeightLiftingEntity
import com.example.myfitnessbuddy.R
import com.example.myfitnessbuddy.databinding.FragmentLogExerciseBinding
import com.example.myfitnessbuddy.viewModels.AddLogViewModel
import com.example.myfitnessbuddy.viewModelsFactory.AddLogViewModelFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_log_exercise.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread

import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.concurrent.Future


class AddLogFragment : Fragment() {

    var c: Calendar = Calendar.getInstance()
    var exerciseType = -1
    var exerciseId = -1L
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("FRAG", "FRAGMENT INITIATED")

        val mainView: FragmentLogExerciseBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_log_exercise,
            container,
            false
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

        // Adding View Model
        val application = requireNotNull(this.activity).application
        val weightLiftingDao = FitDatabase.getInstance(application).weightLiftingDao()
        val runningDao = FitDatabase.getInstance(application).runningDao()
        val swimmingDao = FitDatabase.getInstance(application).swimmingDao()


        val viewModelFactory = AddLogViewModelFactory(
            weightLiftingDao,
            runningDao,
            swimmingDao,
            application
        )
        val addLogViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddLogViewModel::class.java)
        mainView.logViewModel = addLogViewModel


        try {

            exerciseType = requireArguments().getInt("exerciseType")
            exerciseId = requireArguments().getLong("exerciseId")
            mainView.btnAdd.text = "UPDATE"
            Log.d("EXERCISE_TYPE", "EXERCISE TYPE IS "+exerciseType)

            addLogViewModel.selectedSpinnerIndex = exerciseType

            if(exerciseType == 0 ){
                doAsync {
                    val exercise : WeightLiftingEntity = weightLiftingDao.get(exerciseId)
                    addLogViewModel.exerciseId = exerciseId
                    addLogViewModel.insertMode = false
                    addLogViewModel.currentDate = exercise.date
                    addLogViewModel.input1 = exercise.reps.toString()
                    addLogViewModel.input2 = exercise.sets.toString()
                    addLogViewModel.input3 = exercise.weight.toString()


                }
            }
            if(exerciseType == 1 ){
                doAsync {
                    val exercise : RunningEntity = runningDao.get(exerciseId)
                    addLogViewModel.exerciseId = exerciseId
                    addLogViewModel.insertMode = false
                        addLogViewModel.currentDate = exercise.date
                        addLogViewModel.input1 = exercise.distance.toString()
                        addLogViewModel.input2 = exercise.speed.toString()
                    uiThread {
                        mainView.input3.visibility = View.GONE
                    }

                }

            }
            if(exerciseType == 2 ){
                doAsync {
                    val exercise : SwimmingEntity = swimmingDao.get(exerciseId)
                    addLogViewModel.exerciseId = exerciseId
                    addLogViewModel.insertMode = false
                    addLogViewModel.currentDate = exercise.date
                    addLogViewModel.input1 = exercise.speed.toString()
                    addLogViewModel.input2 = exercise.kicks.toString()
                    addLogViewModel.input3 = exercise.time.toString()


                }
            }


        }catch(e : Exception) {
            print(e)
        }


        if(exerciseType==-1){
            val current = DateFormat.format("MMMM",c) as String
            val day = c.get(Calendar.DAY_OF_MONTH)
            val year = c.get(Calendar.YEAR)
            val hour = c.get(Calendar.HOUR)
            val min = c.get(Calendar.MINUTE)

            val time = hour.toString() + ":" +min

            val date12Format = SimpleDateFormat("hh:mm a")

            val date24Format = SimpleDateFormat("HH:mm")
            addLogViewModel.currentDate =
                current+" "+day+", "+year+"  "+date12Format.format(date24Format.parse(time))
        }
        // Spinner
        val exerciseList = resources.getStringArray(R.array.exercisesList)
        val adapter = ArrayAdapter(
            container!!.context,
            android.R.layout.simple_list_item_1,
            exerciseList
        )
        mainView.spinner.adapter = adapter

        mainView.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(exerciseType==-1) {
                    Log.d("POSITION", "" + position)
                    addLogViewModel.selectedSpinnerIndex = position
                    if (position == 0) {
                        weightLiftingIsSelected()
                        mainView.exerciseImage.setImageResource(R.drawable.weightlifting)
                    }
                    if (position == 1) {
                        runningIsSelected()
                        mainView.exerciseImage.setImageResource(R.drawable.running)
                    }
                    if (position == 2) {
                        swimmingIsSelected()
                        mainView.exerciseImage.setImageResource(R.drawable.swimming)
                    }
                }else{
                    mainView.spinner.setSelection(exerciseType)
                }
            }

        }


        //Date Picker
        mainView.dateField.setOnClickListener {

            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    c.set(year, monthOfYear, dayOfMonth)
                    val calendar: Calendar = Calendar.getInstance()
                    val hour = calendar.get(Calendar.HOUR)
                    val minute = calendar.get(Calendar.MINUTE)
                    val timePickerDialog = TimePickerDialog(
                        requireContext(), TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                            val myHour = i
                            val myMinute = i2
                            val nameMonth = DateFormat.format("MMMM", c) as String

                            val time = myHour.toString() + ":" +myMinute

                            val date12Format = SimpleDateFormat("hh:mm a")

                            val date24Format = SimpleDateFormat("HH:mm")

                            dateField.text = nameMonth + " " + dayOfMonth + ", " + year + "    " +
                                    date12Format.format(date24Format.parse(time))

                        }, hour, minute,
                        DateFormat.is24HourFormat(requireContext())
                    )
                    timePickerDialog.show()
                },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
        return mainView.root
    }

    fun weightLiftingIsSelected() {
        makeAllVisible()
        input1.setHint("Reps")
        input1.setText("")
        input2.setHint("Sets")
        input2.setText("")
        input3.setHint("Weight")
        input3.setText("")

    }

    fun runningIsSelected() {
        makeAllVisible()
        input1.setHint("Distance")
        input1.setText("")
        input2.setHint("Speed")
        input2.setText("")
        input3.visibility = View.GONE
    }

    fun swimmingIsSelected() {
        makeAllVisible()
        input1.setHint("Speed")
        input1.setText("")
        input2.setHint("Kicks")
        input2.setText("")
        input3.setHint("Time")
        input3.setText("")
    }

    fun makeAllVisible() {
        input1.visibility = View.VISIBLE
        input2.visibility = View.VISIBLE
        input3.visibility = View.VISIBLE
    }

    fun OnUpdate(){

    }

}
