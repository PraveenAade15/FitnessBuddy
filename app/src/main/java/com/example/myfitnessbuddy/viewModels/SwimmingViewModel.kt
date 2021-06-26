package com.example.myfitnessbuddy.viewModels

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.myfitnessbuddy.Dao.SwimmingDao
import com.example.myfitnessbuddy.Entity.SwimmingEntity
import com.example.myfitnessbuddy.UserObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class SwimmingViewModel(val swimmingDao: SwimmingDao) : ViewModel() {

    fun insert(view: View, date:String, speed:String, kicks:String, time:String){
        Log.d("INSERT", "TRYING TO ADD SWIMMING LOG")
        if(kicks!!.isEmpty() || speed!!.isEmpty() || time.isEmpty()){
            Toast.makeText(view.context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
        }else{
            doAsync {
                val newExercise = SwimmingEntity(0,
                    UserObject.user.id,date,speed.toFloat(),kicks.toInt(),time.toFloat())
                Log.d("INSERT", ""+newExercise)
                swimmingDao.insert(newExercise)
                uiThread {
                    Log.d("INSERT", "INSERT DONE")
                    Toast.makeText(view.context, "Log inserted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun update(view: View, id:Long,date:String, speed:String, kicks:String, time:String){
        if(kicks!!.isEmpty() || speed!!.isEmpty() || time.isEmpty()){
            Toast.makeText(view.context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
        }else {
            val newExercise = SwimmingEntity(
                id,UserObject.user.id, date,
                speed.toFloat(), kicks.toInt(),time.toFloat()
            )
            doAsync {
                swimmingDao.update(newExercise)
                Log.d("UPDATE", "update: Updated running exercise")
                uiThread {
                    Toast.makeText(view.context, "Log Updated", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}