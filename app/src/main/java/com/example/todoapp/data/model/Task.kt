package com.example.todoapp.data.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Task(
    val taskListId:String,
    @PrimaryKey var id: String ,
    var title: String,
    var details: String="",
    var completionDate: Long=0,
    var isComplete: Int=0,
    //var timeStatus: TimeStatus,
    var createdAt: Long=0,
)


data class TaskDb(
    val taskListId:String,
    var title: String,
    var details: String="",
    var completionDate: Long=0,
    var isComplete: Int=0,
    //var timeStatus: TimeStatus,
    var createdAt: Long=0,
)


