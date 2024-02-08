package com.github.com.ebrahimi16153.todo.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel


@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    navigateToList: (Action) -> Unit,
    task: ToDoTask?
) {


    val context = LocalContext.current


    Scaffold(topBar = {
        TaskAppBar(
            task = task,

            // handel every action what work
            navigateToList = { action ->

                if (action == Action.NO_ACTION) {

                    navigateToList(action)

                } else {

                    if (sharedViewModel.validateTitleAndDescription()) {
                        navigateToList(action)
                    } else {

                        showToast(context = context)
                    }
                }
            },
        )
    }) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {


            TaskContent(
                title = sharedViewModel.title.value,
                onTitleChange = { onTitleChange ->
//                    sharedViewModel.title.value = onTitleChange
                    // set a limit to set title
                    sharedViewModel.updateTitle(newTitle = onTitleChange)
                },
                priority = sharedViewModel.priority.value,
                onPrioritySelected = { onPriorityChange ->
                    sharedViewModel.priority.value = onPriorityChange
                },
                description = sharedViewModel.description.value,
                onDescriptionChange = { onDescriptionChange ->
                    sharedViewModel.description.value = onDescriptionChange
                }
            )

        }

    }

}

fun showToast(context: Context) {

    Toast.makeText(context, "Fields Can't be Empty 😒", Toast.LENGTH_SHORT).show()

}