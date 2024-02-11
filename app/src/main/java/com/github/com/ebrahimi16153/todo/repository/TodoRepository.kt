package com.github.com.ebrahimi16153.todo.repository

import com.github.com.ebrahimi16153.todo.data.TodoDao
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val allTask = todoDao.getAll()
    val getAllByLowPriority = todoDao.sortByLowPriority()
    val getAllByHighPriority = todoDao.sortByHighPriority()
    val getAllByMediumPriority = todoDao.sortByMediumPriority()
    fun getBySearch(searchValue: String) = todoDao.getAllBySearch(searchQuery = searchValue)
    fun getTask(taskId: Int) = todoDao.getTodoTask(id = taskId)

    suspend fun insert(toDoTask: ToDoTask) = todoDao.insert(toDoTask)
    suspend fun update(toDoTask: ToDoTask) = todoDao.update(toDoTask)
    suspend fun delete(toDoTask: ToDoTask) = todoDao.deleteTodoTask(toDoTask)
    suspend fun  deleteAll() = todoDao.deleteAll()

}