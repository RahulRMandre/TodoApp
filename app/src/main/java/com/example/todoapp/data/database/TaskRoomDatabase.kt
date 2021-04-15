package com.example.todoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.data.dao.AllTaskTitleDao
import com.example.todoapp.data.dao.TaskDao
import com.example.todoapp.data.dao.UserDao
import com.example.todoapp.data.model.AllTaskTitles
import com.example.todoapp.data.model.Task
import com.example.todoapp.data.model.TitleType
import com.example.todoapp.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class, User::class, AllTaskTitles::class],version = 1,exportSchema = false)
abstract class TaskRoomDatabase :RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
    abstract fun allTaskTitlesDao(): AllTaskTitleDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var allTaskTitleDao = database.allTaskTitlesDao()

                    // Delete all content here.
                    //allTaskTitleDao.deleteAll()

                    // Add sample words.
                    var word = AllTaskTitles("Hello",TitleType.CUSTOM)
                    allTaskTitleDao.insert(word)
                    word = AllTaskTitles("World!",TitleType.CUSTOM)
                    allTaskTitleDao.insert(word)


                    word = AllTaskTitles("TODO!",TitleType.CUSTOM)
                    allTaskTitleDao.insert(word)

                    val userDao=database.userDao()
                    var user=User("Rahul","rahul.gamil.com")
                    userDao.loginUser(user)
                }
            }
        }
    }


    companion object{
        @Volatile
        private var INSTANCE:TaskRoomDatabase?=null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TaskRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }


    }
}