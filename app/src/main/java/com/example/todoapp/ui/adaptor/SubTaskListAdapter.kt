package com.example.todoapp.ui.adaptor

import  android.graphics.Paint
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.model.SubTask
import com.example.todoapp.databinding.SubtaskListViewBinding
import com.example.todoapp.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SubTaskListAdapter(private val dataSet: TaskViewModel) :
    RecyclerView.Adapter<SubTaskListAdapter.ViewHolder>() {

    class ViewHolder(val binding: SubtaskListViewBinding) : RecyclerView.ViewHolder(binding.root)

     var subtask=SubTask("","","",0)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = SubtaskListViewBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {

        val currentSubTask: SubTask? = dataSet.subTask.value?.get(position)
        if (currentSubTask != null) {
            viewholder.binding.subtask = currentSubTask
        }

        viewholder.binding.delete.setOnClickListener {
            CoroutineScope(IO).launch {
                dataSet.deleteSubTask(dataSet.subTask!!.value!![position].id,dataSet.subTask!!.value!![position].taskId)
                notify()

            }

        }

        viewholder.binding.taskCompleted.setOnClickListener {
            if(dataSet.subTask.value?.get(position)!!.finish ==1){
                taskDone(0,viewholder.binding)
                dataSet.subTask.value?.get(position)!!.finish =0
            }
            else{
                taskDone(1,viewholder.binding)
                dataSet.subTask.value?.get(position)!!.finish =1
            }
        }

        viewholder.binding.title.setOnEditorActionListener{ _, actionId, _ ->
            if(actionId==EditorInfo.IME_ACTION_NEXT){
                enterPressed(position)
                true
            }
            false
        }
    }

     private fun enterPressed(position: Int) {
         CoroutineScope(IO).launch {
             dataSet.updateSubTask(dataSet.subTask.value!!.get(position))
         }

    }

    private suspend fun notify() {
        CoroutineScope(Dispatchers.Main).launch {
            notifyDataSetChanged()
        }
    }
    override fun getItemCount() = dataSet.subTask.value?.size ?: 0


    /**
     *handles task Completed selection
     **/
    private fun taskDone(done: Int,binding: SubtaskListViewBinding) {
        when (done) {
            1 -> {
                binding.taskCompleted.setImageResource(R.drawable.ic_circle_filled)
                binding.title.paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG or binding.title.paintFlags)
            }
            0 -> {

                binding.taskCompleted.setImageResource(R.drawable.ic_circle)
                binding.title.paintFlags =
                    (Paint.STRIKE_THRU_TEXT_FLAG.inv() and binding.title.paintFlags)
            }
        }
    }
}

