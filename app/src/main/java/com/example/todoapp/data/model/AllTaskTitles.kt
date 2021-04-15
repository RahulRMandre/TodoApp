package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AllTaskTitles (
     @PrimaryKey
     var title:String,
     var titleType:TitleType
)