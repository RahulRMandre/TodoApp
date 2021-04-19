package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllTaskTitles (

     var taskListTitle:String,
     var userId:String,
     @PrimaryKey
     var id:String
)


data class AllTaskTitlesDb(
     val taskListTitle:String,
     val userId:String,
)