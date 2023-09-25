package com.example.a6homework1task.model

import java.io.Serializable

data class TaskModel(
    var title: String,
    var isChecked: Boolean = false
) : Serializable
