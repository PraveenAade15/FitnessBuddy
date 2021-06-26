package com.example.myfitnessbuddy.Fragments
// Vishvakumar Mavani
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_view_logs.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import project.st991497190.vishvakumar.Entity.WeightLiftingEntity
import project.st991497190.vishvakumar.RecyclerView.MyRecyclerViewRunning
import project.st991497190.vishvakumar.RecyclerView.MyRecyclerViewSwimming
import project.st991497190.vishvakumar.RecyclerView.MyRecyclerViewWeightLifting
import project.st991497190.vishvakumar.viewModels.ViewLogsViewModel
import project.st991497190.vishvakumar.viewModelsFactory.ViewLogViewModelFactory
import project.st991497190.vishvakumar.Database.FitDatabase
import project.st991497190.vishvakumar.R
import project.st991497190.vishvakumar.UserObject
import project.st991497190.vishvakumar.databinding.FragmentViewLogsBinding

class ViewLogsFragment : Fragment() {
    private lateinit var database : FitDatabase
    private var weightLiftingList = listOf<WeightLiftingEntity>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding: FragmentViewLogsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_view_logs,container,false)

        //Database
        val application = requireNotNull(this.activity).application
        database = FitDatabase.getInstance(application)

        // Binding
        val exerciseList = resources.getStringArray(R.array.exercisesList)
        val adapter = ArrayAdapter(container!!.context,android.R.layout.simple_list_item_1,exerciseList)
        binding.spinner2.adapter = adapter
        binding.recyclerView.adapter = MyRecyclerViewWeightLifting(weightLiftingList.toMutableList())
        getWeightLiftingData()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        //View Model
        val weightLiftingDao = database.weightLiftingDao()
        val runningDao = database.runningDao()
        val swimmingDao = database.swimmingDao()
        val viewModelFactory = ViewLogViewModelFactory(weightLiftingDao,runningDao,swimmingDao,application)
        val viewLogsViewModel = ViewModelProviders.of(this,viewModelFactory).get(ViewLogsViewModel::class.java)
        binding.viewLogsViewModel = viewLogsViewModel

        //Spinner
        binding.spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("POSITION", ""+position)
                if (position == 0){
                    getWeightLiftingData()
                }
                if(position == 1){
                    getRunningData()
                }
                if(position == 2){
                    getSwimmingData()
                }

            }

        }

        return binding.root

    }

    private fun getWeightLiftingData(){
        doAsync {
            val list = database.weightLiftingDao().getAll(UserObject.user.id)
            uiThread {
                recyclerView.adapter = MyRecyclerViewWeightLifting(list.toMutableList())
            }
        }

    }
    private fun getRunningData(){
        doAsync {
            val list = database.runningDao().getAll(UserObject.user.id)
            uiThread {
                recyclerView.adapter = MyRecyclerViewRunning(list.toMutableList())
            }
        }

    }
    private fun getSwimmingData(){
        doAsync {
            val list = database.swimmingDao().getAll(UserObject.user.id)
            uiThread {
                recyclerView.adapter = MyRecyclerViewSwimming(list.toMutableList())
            }
        }

    }

}