package com.github.com.ebrahimi16153.todo.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.navigation.Action


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAppBar(
    task: ToDoTask?,
    navigateToList: (Action) -> Unit
) {
    if (task == null){
        NewTaskAppBar(navigateToList = navigateToList)
    }else{
        ExistTaskBar(navigateToList = navigateToList, task = task)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(navigateToList: (Action) -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {

            Text(
                text = "New Task",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )


        },
        navigationIcon = {
            IconButton(onClick = { navigateToList(Action.NO_ACTION) }) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }


        },
        actions = {
            IconButton(onClick = { navigateToList(Action.ADD) }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }


        })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistTaskBar(navigateToList: (Action) -> Unit,task: ToDoTask?) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            Text(
                text = task!!.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

        }, navigationIcon = {

            IconButton(onClick = { navigateToList(Action.NO_ACTION) }) {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }

        }, actions = {

            IconButton(onClick = { navigateToList(Action.DELETE) }) {

                Icon(
                    imageVector = Icons.Default.Delete,

                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }


            IconButton(onClick = { navigateToList(Action.UPDATE) }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        })


}


