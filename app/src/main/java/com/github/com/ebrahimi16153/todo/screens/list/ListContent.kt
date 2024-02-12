package com.github.com.ebrahimi16153.todo.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.component.EmptyContent
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.data.RequestState
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.util.SearchBarState
import kotlinx.coroutines.delay

@Composable
fun ListContent(
    navigateToDoTask: (taskId: Int) -> Unit,
    swipeToDelete: (action: Action, task: ToDoTask) -> Unit,
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

                HandleListContent(
                    tasks = searchOfTask.data,
                    navigateToDoTask = navigateToDoTask,
                    swipeToDelete = swipeToDelete
                )

            }
        } else {
            when (priorityState.data) {
                Priority.None -> {
                    if (allTasks is RequestState.Success) {
                        HandleListContent(
                            tasks = allTasks.data,
                            navigateToDoTask = navigateToDoTask,
                            swipeToDelete = swipeToDelete
                        )
                    }
                }

                Priority.High -> {

                    HandleListContent(
                        tasks = taskByHigh,
                        navigateToDoTask = navigateToDoTask,
                        swipeToDelete = swipeToDelete
                    )
                }

                Priority.Medium -> {
                    HandleListContent(
                        tasks = taskByMedium,
                        navigateToDoTask = navigateToDoTask,
                        swipeToDelete = swipeToDelete
                    )
                }

                Priority.Low -> {
                    HandleListContent(
                        tasks = taskByLow,
                        navigateToDoTask = navigateToDoTask,
                        swipeToDelete = swipeToDelete
                    )
                }
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleListContent(
    swipeToDelete: (action: Action, task: ToDoTask) -> Unit,
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

                //  handel swipe to delete
                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

                if (isDismissed && dismissDirection  == DismissDirection.EndToStart){
                  LaunchedEffect(key1 = dismissState ){

                      delay(300)
                      swipeToDelete(Action.DELETE,task)

                    }
                }

                var itemAppeared  by remember {
                    mutableStateOf(false)
                }

                LaunchedEffect(key1 = true){
                    itemAppeared = true
                }



                val degreeAnimation =
                    if (dismissState.targetValue == DismissValue.Default) 0f else 45f

              // first animation

                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed ,
                    enter = expandVertically(animationSpec = tween(durationMillis = 300)),
                    exit = shrinkVertically (animationSpec = tween(durationMillis = 300))
                ) {

                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        background = { RedBackground(degree = degreeAnimation) },
                        dismissContent = {

                            TaskItem(task = task, navigateToDoTask = navigateToDoTask)

                        })
                }


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


@Composable
fun RedBackground(degree: Float) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.error),
        contentAlignment = Alignment.CenterEnd,
    ) {

        Icon(
            modifier = Modifier.rotate(degree),
            imageVector = Icons.Default.Delete,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onError
        )

    }

}


@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = ToDoTask(id = 1, description = "", priority = Priority.Medium),
        navigateToDoTask = {})
}