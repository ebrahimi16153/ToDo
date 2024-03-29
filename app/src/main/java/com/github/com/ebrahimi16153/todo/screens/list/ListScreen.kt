package com.github.com.ebrahimi16153.todo.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.github.com.ebrahimi16153.todo.R
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel
import kotlinx.coroutines.launch


@Composable
fun ListScreen(
    navigateToTask: (taskId: Int) -> Unit,
    shearedViewModel: SharedViewModel,
) {


    // handel List of Task
    LaunchedEffect(key1 = true) {
        shearedViewModel.getAllTask()
        shearedViewModel.priorityFromDataStore()
    }
    // list of all task
    val tasks by shearedViewModel.allTask.collectAsState()
    // list of search tasks

    val taskByLow by shearedViewModel.getTasksByLowPriority.collectAsState()
    val taskByMedium by shearedViewModel.getTasksByMediumPriority.collectAsState()
    val taskByHigh by shearedViewModel.getTasksByHighPriority.collectAsState()

    val searchTask by shearedViewModel.searchTask.collectAsState()
    // get priority from data Store
    val priorityState = shearedViewModel.getSortByPriorityFromDataStore.collectAsState()

    // handel actions
    val action by shearedViewModel.action

    // first way to define val
    val searchTextState by remember {
        shearedViewModel.searchTextState
    }

    val searchBarState by remember {
        shearedViewModel.searchBarState
    }

    // snackBar
    val snackBarHostState = remember { SnackbarHostState() }

    DisplaySnackBar(
        snackbarHostState = snackBarHostState,
        handelDataBaseAction = { shearedViewModel.handelAction(action = action) },
        action = action,
        taskTitle = shearedViewModel.title.value,
        onUndoClicked = {
            shearedViewModel.action.value = it
        }
    )

    shearedViewModel.handelAction(action = action)

    // second way to define val
//    val searchTextState :String by shearedViewModel.searchTextState
//    val searchBarState :SearchBarState by shearedViewModel.searchBarState

    Scaffold(
        topBar = {

            ListAppBar(
                shearedViewModel = shearedViewModel,
                searchBarState = searchBarState,
                textState = searchTextState
            )


        },
        floatingActionButton = {
            FabButton(navigateToTask = navigateToTask)
        }, snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    )
    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ListContent(
                priorityState = priorityState.value,
                navigateToDoTask = navigateToTask,
                taskByLow = taskByLow,
                taskByMedium = taskByMedium,
                taskByHigh = taskByHigh,
                allTasks = tasks,
                searchOfTask = searchTask,
                searchAppBarState = searchBarState,
                swipeToDelete = { action, task ->

                    shearedViewModel.action.value = action
                    shearedViewModel.updateTask(task)

                }
            )
        }

    }


}

@Composable
fun FabButton(
    navigateToTask: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = { navigateToTask(-1) }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    snackbarHostState: SnackbarHostState,
    handelDataBaseAction: () -> Unit,
    action: Action,
    taskTitle: String,
    onUndoClicked: (Action) -> Unit
) {

    handelDataBaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {

        if (action != Action.NO_ACTION) {
            scope.launch {
                val result = snackbarHostState
                    .showSnackbar(
                        message = setMessage(action = action , taskTitle =  taskTitle),
                        actionLabel = setActionLabel(action = action),
                        duration = SnackbarDuration.Short
                    )
                undo(action = action, snackbarResult = result, onUndoClicked = onUndoClicked)
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> {
            "All Tasks Removed"
        }

        else -> {
            "${action.name} :$taskTitle"
        }
    }
}

private fun setActionLabel(action: Action): String {
    return if (action == Action.DELETE) {
        "Undo"
    } else {
        "Ok"
    }
}

private fun undo(

    action: Action,
    snackbarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit

) {

    if (snackbarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }


}