package com.example.todoapp.data.model

import androidx.room.Entity

@Entity
data class SubTask(
    var title: String,
    var isComplete: Boolean=false
)
