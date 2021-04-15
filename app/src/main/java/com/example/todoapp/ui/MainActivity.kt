package com.example.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.R

import com.example.todoapp.data.database.TaskRoomDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // val dbHelper = DatabaseHelperImpl(TaskRoomDatabase.getDatabase( this ))
    }
}