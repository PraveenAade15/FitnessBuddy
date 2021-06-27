package com.example.myfitnessbuddy.RecyclerView

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessbuddy.Database.FitDatabase
import com.example.myfitnessbuddy.Entity.RunningEntity
import com.example.myfitnessbuddy.R
import kotlinx.android.synthetic.main.list_view.view.*


class MyRecyclerViewRunning(private var sampleList:MutableList<RunningEntity>): RecyclerView.Adapter<MyRecyclerViewRunning.MyViewHolderRunning>() {
    class MyViewHolderRunning(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.logDate
        val textView1: TextView = itemView.tView1
        val textView2 : TextView = itemView.tView2
        val textView3 : TextView = itemView.tView3
        val btnDeleteLog : ImageView = itemView.btnDeleteLog
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderRunning {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false);
        val viewHolder = MyViewHolderRunning(itemView)
        itemView.setOnClickListener{v->
            Toast.makeText(itemView.logDate.context,"You have selected ",Toast.LENGTH_SHORT).show()
            var bundle = Bundle()
            bundle.putInt("exerciseType",1)
            Log.d("POS", "onCreateViewHolder: Adapter position is" +viewHolder.adapterPosition)
            Log.d("POS", "onCreateViewHolder: ID is" +sampleList[viewHolder.adapterPosition].id)
            bundle.putLong("exerciseId",sampleList[viewHolder.adapterPosition].id)

            v.findNavController()?.navigate(R.id.action_viewLogsFragment_to_addLogFragment,bundle);
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolderRunning, position: Int) {
        val currentItem = sampleList[position]
        holder.date.text = currentItem.date
        holder.textView1.text = ""+currentItem.distance+" KM"
        holder.textView2.text = ""+currentItem.speed+" Kmph"
        holder.textView3.visibility = View.GONE
        holder.btnDeleteLog.setOnClickListener { v ->
            val builder = AlertDialog.Builder(v.context)
            builder.setTitle("Delete Conformation")
            builder.setMessage("Are you sure you want to delete the log ?")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(
                    v.context,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(
                    v.context,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }

            builder.setPositiveButton("Yes") { _, _ ->
                Toast.makeText(
                    v.context,
                    "Deleting log", Toast.LENGTH_SHORT
                ).show()
                FitDatabase.getInstance(v.context).runningDao().delete(currentItem.id)
                sampleList.removeAt(position)
                notifyDataSetChanged()
            }

            builder.setNegativeButton("No") { _, _ ->

                Toast.makeText(
                    v.context,
                    "Not deleting log", Toast.LENGTH_SHORT
                ).show()
            }

            builder.show()
        }
    }

    override fun getItemCount() = sampleList.size
}