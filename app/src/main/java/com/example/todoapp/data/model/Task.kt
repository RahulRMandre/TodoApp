package com.example.todoapp.data.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Task(
    @PrimaryKey var id:String,
    var title: String,
    var details: String="",
    var completionDate: Long=0,
    var isComplete: Boolean=false,
    var timeStatus: TimeStatus,
    val createdAt: Long=0,
)





