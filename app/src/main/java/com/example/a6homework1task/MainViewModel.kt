package com.example.a6homework1task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a6homework1task.model.TaskModel
import java.lang.IllegalArgumentException

class MainViewModel : ViewModel() {

    private val _taskList = MutableLiveData<MutableList<TaskModel>>()
    val taskList: LiveData<MutableList<TaskModel>> get() = _taskList
    private var currentTask = mutableListOf<TaskModel>()

    fun addTask(title: String) {
        currentTask.add(TaskModel(title, false))
        _taskList.value = currentTask
    }

    fun checkTask(position: Int) {
        currentTask[position].isChecked = !currentTask[position].isChecked
    }

    fun removeTask(task: TaskModel) {
        currentTask.remove(task)
        _taskList.value = currentTask
    }
}

