package com.github.com.ebrahimi16153.todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.util.Constants

@Entity(tableName = Constants.TO_DO_TASK_TABLE)
data class ToDoTask(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String = "",
    val description:String ="",
    val priority: Priority = Priority.High


)