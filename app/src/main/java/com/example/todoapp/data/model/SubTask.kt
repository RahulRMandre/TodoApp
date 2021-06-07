package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubTask(
    @PrimaryKey
    val id:String,
    val taskId:String,
    var title: String,
    var finish: Int
)

data class SubTaskDb(
    val taskId:String,
    var title: String,
    var finish: Int
)

