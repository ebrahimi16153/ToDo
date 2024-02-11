package com.github.com.ebrahimi16153.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM ${Constants.TO_DO_TASK_TABLE} ORDER BY id ASC")
    fun getAll(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM ${Constants.TO_DO_TASK_TABLE} WHERE id=:id")
    fun getTodoTask(id: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: ToDoTask)


    @Update
    suspend fun update(task: ToDoTask)


    @Delete
    suspend fun deleteTodoTask(task: ToDoTask)

    @Query("DELETE FROM ${Constants.TO_DO_TASK_TABLE}")
    suspend fun deleteAll()


    @Query("Select * FROM ${Constants.TO_DO_TASK_TABLE} WHERE title LIKE :searchQuery OR description LIKE :searchQuery ")
    fun getAllBySearch(searchQuery: String): Flow<List<ToDoTask>>


    @Query("SELECT * FROM ${Constants.TO_DO_TASK_TABLE} ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END ")
    fun sortByLowPriority(): Flow<List<ToDoTask>>


    @Query("SELECT * FROM ${Constants.TO_DO_TASK_TABLE} ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END ")
    fun sortByHighPriority(): Flow<List<ToDoTask>>


    @Query("SELECT * FROM ${Constants.TO_DO_TASK_TABLE} ORDER BY CASE WHEN priority LIKE 'M%' THEN 1 WHEN priority LIKE 'H%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END ")
    fun sortByMediumPriority(): Flow<List<ToDoTask>>

}