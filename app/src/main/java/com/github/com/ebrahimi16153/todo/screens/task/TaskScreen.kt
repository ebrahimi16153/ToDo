package com.github.com.ebrahimi16153.todo.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel


@Composable
fun TaskScreen(
    taskId:Int,
    sharedViewModel: SharedViewModel,
    navigateToList: (Action) -> Unit
) {

   sharedViewModel.getTaskById(taskId = taskId)
    val task = sharedViewModel.task.collectAsState()

    Scaffold(topBar = {
        TaskAppBar(
            task = task.value,
            navigateToList = navigateToList,
        )
    }) {


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

        }

    }

}