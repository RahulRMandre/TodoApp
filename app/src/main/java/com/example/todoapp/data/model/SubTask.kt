package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val parentId:String,
    var title: String,
    var isComplete: Boolean=false
)


