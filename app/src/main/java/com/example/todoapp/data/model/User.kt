package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name: String,
    @PrimaryKey val email: String
)
