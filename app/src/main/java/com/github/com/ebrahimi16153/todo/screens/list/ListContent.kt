package com.github.com.ebrahimi16153.todo.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.component.EmptyContent
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.data.RequestState
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.util.SearchBarState

@Composable
fun ListContent(
    navigateToDoTask: (taskId: Int) -> Unit,
    allTasks: RequestState<List<ToDoTask>>,
    searchOfTask: RequestState<List<ToDoTask>>,
    searchAppBarState: SearchBarState,
    priorityState: RequestState<Priority>,
    taskByLow: List<ToDoTask>,
    taskByMedium: List<ToDoTask>,
    taskByHigh: List<ToDoTask>
) {


    if (priorityState is RequestState.Success) {
        // when searchBarState is triggered  -> searchTasks must handel
        if (searchAppBarState == SearchBarState.Triggered) {

            // handel what happened when Request is data,Error,loading
            if (searchOfTask is RequestState.Success) {

                HandleListContent(tasks = searchOfTask.data, navigateToDoTask = navigateToDoTask)

            }
        } else {
            when (priorityState.data) {
                Priority.None -> {
                    if (allTasks is RequestState.Success) {
                        HandleListContent(
                            tasks = allTasks.data,
                            navigateToDoTask = navigateToDoTask
                        )
                    }
                }

                Priority.High -> {

                    HandleListContent(tasks = taskByHigh, navigateToDoTask = navigateToDoTask)
                }

                Priority.Medium -> {
                    HandleListContent(tasks = taskByMedium, navigateToDoTask = navigateToDoTask)
                }

                Priority.Low -> {
                    HandleListContent(tasks = taskByLow, navigateToDoTask = navigateToDoTask)
                }
            }

        }
    }


}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToDoTask: (taskId: Int) -> Unit
) {
    // handel what happened when DataBase is empty or searchQuery Result is empty
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn {
            items(
                items = tasks,
                key = { task -> task.id })
            { task ->
                TaskItem(task = task, navigateToDoTask = navigateToDoTask)
            }
        }
    }
}

@Composable
fun TaskItem(
    task: ToDoTask,
    navigateToDoTask: (taskId: Int) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { navigateToDoTask(task.id) }, color = MaterialTheme.colorScheme.secondary,
        shape = RectangleShape
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Row {
                Text(
                    modifier = Modifier.weight(0.8f),
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Canvas(modifier = Modifier.size(15.dp), onDraw = {
                    drawCircle(color = task.priority.color)
                })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = task.description, style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSecondary
            )

        }

    }


}

@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = ToDoTask(id = 1, description = "", priority = Priority.Medium),
        navigateToDoTask = {})
}