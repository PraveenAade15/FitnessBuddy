package com.example.myfitnessbuddy.viewModels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.myfitnessbuddy.Dao.RunningDao
import com.example.myfitnessbuddy.Dao.SwimmingDao
import com.example.myfitnessbuddy.Dao.WeightLiftingDao


class AddLogViewModel(val weightLiftingDao: WeightLiftingDao, val runningDao: RunningDao, val swimmingDao: SwimmingDao, application: Application) : ViewModel() {
    var weightLiftingViewModel = WeightLiftingViewModel(weightLiftingDao)
    var runningViewModel = RunningViewModel(runningDao)
    var swimmingViewModel = SwimmingViewModel(swimmingDao)
    var selectedSpinnerIndex = 0
    var insertMode = true
    var exerciseId  = -1L
    var _currentDate : String? = ""


    var currentDate: String?
        get() {
            return _currentDate
        }
        set(value) {
            _currentDate = value
        }

    var _input1: String? = ""
    var _input2: String? = ""
    var _input3: String? = ""

    var input1: String?
        get() {
            return _input1
        }
        set(value) {
            _input1 = value
        }

    var input2: String?
        get() {
            return _input2
        }
        set(value) {
            _input2 = value
        }

    var input3: String?
        get() {
            return _input3
        }
        set(value) {
            _input3 = value
        }

    fun onAddButtonClick(view : View){
        // Insert
        if(insertMode) {
            Log.d("INSERT", "INSERT MODE ON")
            print("Add Button Clicked")
            if (selectedSpinnerIndex == 0) {

                weightLiftingViewModel.insert(view, currentDate!!, input1!!, input2!!, input3!!);
            }
            if (selectedSpinnerIndex == 1) {

                runningViewModel.insert(view, currentDate!!, input1!!, input2!!)

            }
            if(selectedSpinnerIndex == 2){
                swimmingViewModel.insert(view,currentDate!!,input1!!,input2!!,input3!!)
            }
        }
        //Update
        else{
            Log.d("INSERT", "INSERT MODE OFF")
            Log.d("UPDATE", "onAddButtonClick: index is "+selectedSpinnerIndex)
            if(exerciseId!=-1L) {
                if (selectedSpinnerIndex == 0) {

                    weightLiftingViewModel.update(
                        view,
                        exerciseId,
                        currentDate!!,
                        input1!!,
                        input2!!,
                        input3!!
                    );
                }
                if (selectedSpinnerIndex == 1) {

                    runningViewModel.update(view,exerciseId, currentDate!!, input1!!, input2!!)

                }
                if (selectedSpinnerIndex == 2) {

                    swimmingViewModel.update(view,exerciseId, currentDate!!, input1!!, input2!!,input3!!)

                }
            }
        }
    }

    fun onDeleteButtonClick(){
        print("Delete button clicked")
        runningViewModel.select()
    }


}