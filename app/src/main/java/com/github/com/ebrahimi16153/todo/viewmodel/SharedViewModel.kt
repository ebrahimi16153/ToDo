package com.github.com.ebrahimi16153.todo.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.data.RequestState
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.repository.TodoRepository
import com.github.com.ebrahimi16153.todo.util.SearchBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    // some value to handel TaskScreen
    val id: MutableState<Int> = mutableIntStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.Low)


    //RequestState is Sealed class that we crated for handel  loading,Error,and data
    // and other hand it is a Result Class

    private val _allTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTask: StateFlow<RequestState<List<ToDoTask>>> = _allTask


    val searchBarState: MutableState<SearchBarState> = mutableStateOf(SearchBarState.Close)
    val searchTextState: MutableState<String> = mutableStateOf("")


    fun getAllTask() {
        // first -> loading
        _allTask.value = RequestState.Loading

        // then fill with data from db or fill with error

        try {
            viewModelScope.launch {
                todoRepository.allTask.collect { listOfAllTask ->
                    _allTask.value = RequestState.Success(data = listOfAllTask)
                }
            }

        } catch (e: Exception) {
            _allTask.value = RequestState.Error(error = e)
        }
    }

    private val _task: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val task: StateFlow<ToDoTask?> = _task
    fun getTaskById(taskId: Int) {

        viewModelScope.launch {

            todoRepository.getTask(taskId = taskId).collect { task ->
                _task.update {
                    task
                }
            }


        }


    }

    // handel taskScreen

    fun initTaskScreenValues(toDoTask: ToDoTask?) {
        if (toDoTask != null) {
            id.value = toDoTask.id
            title.value = toDoTask.title
            description.value = toDoTask.description
            priority.value = toDoTask.priority
        } else {
            title.value = ""
            description.value = ""
            priority.value = Priority.Low
        }

    }

    // set a limit to set title
    fun updateTitle(newTitle: String) {
        if (newTitle.length < 20) {
            title.value = newTitle
        }
    }


    // validate title and description
    fun validateTitleAndDescription(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }


    // CRUD functions

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {

            val task = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            todoRepository.insert(task)


        }
    }

    // update
    private fun update() {
        viewModelScope.launch(Dispatchers.IO) {

            val task = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            todoRepository.insert(task)


        }
    }


    private fun delete(){
        viewModelScope.launch(Dispatchers.IO){
            val task = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            todoRepository.delete(task)
        }
    }

    // handel action -> database

    fun handelAction(action: Action) {

        when (action) {
            Action.ADD -> {
                addTask()
            }

            Action.UPDATE -> {
                update()
            }

            Action.DELETE -> {

                delete()

            }

            Action.DELETE_ALL -> {

            }

            Action.UNDO -> {
                addTask()

            }

            Action.NO_ACTION -> {

            }
        }

        this.action.value = Action.NO_ACTION


    }

}