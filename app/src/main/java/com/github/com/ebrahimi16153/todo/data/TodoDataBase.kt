package com.github.com.ebrahimi16153.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask


@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class TodoDataBase:RoomDatabase() {

    abstract val todoDao:TodoDao

}