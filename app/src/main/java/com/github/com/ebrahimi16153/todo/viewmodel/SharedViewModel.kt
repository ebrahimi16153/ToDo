package com.github.com.ebrahimi16153.todo.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.repository.TodoRepository
import com.github.com.ebrahimi16153.todo.util.SearchBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {


    private val _allTask = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTask: StateFlow<List<ToDoTask>> = _allTask


    val searchBarState: MutableState<SearchBarState> = mutableStateOf(SearchBarState.Close)
    val searchTextState: MutableState<String> = mutableStateOf("")


    fun getAllTask() {
        viewModelScope.launch {
            todoRepository.allTask.collect {
                _allTask.value = it
            }
        }
    }


}