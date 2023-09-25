package com.example.a6homework1task.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.a6homework1task.databinding.ItemMainTaskBinding
import com.example.a6homework1task.model.TaskModel

class MainFragmentAdapter(
    private val listener: Listener
) :
    Adapter<MainFragmentAdapter.ViewHolder>() {

    private val _task = mutableListOf<TaskModel>()
    private val task get() = _task

    fun addTask(taskList: List<TaskModel>) {
        _task.clear()
        _task.addAll(taskList)
        notifyItemRangeInserted(_task.size, task.size - _task.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_task[position])
    }

    override fun getItemCount(): Int {
        return _task.size
    }

    inner class ViewHolder(private val binding: ItemMainTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.chBox.isChecked = taskModel.isChecked
            itemView.setOnLongClickListener() {
                listener.onLongClickItem(taskModel)
                true
            }
        }
    }

    interface Listener {
        fun onLongClickItem(task: TaskModel)
        fun onClickItem(position: Int)
    }
}
