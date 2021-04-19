package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name: String,
    @PrimaryKey val email: String,
    val password:String,
    val id: String?
)

data class UserDb(
    val name: String,
    val email: String,
    val password:String,
)
