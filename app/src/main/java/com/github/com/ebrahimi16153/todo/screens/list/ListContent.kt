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
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.component.EmptyContent
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.data.RequestState
import com.github.com.ebrahimi16153.todo.data.models.ToDoTask

@Composable
fun ListContent(
    navigateToDoTask: (taskId: Int) -> Unit,
    listOfTask: RequestState<List<ToDoTask>>
) {

    // handel what happened when Request is data,Error,loading

    if (listOfTask is RequestState.Success){

        // handel what happened when DataBase is empty


        if (listOfTask.data.isEmpty()){
            EmptyContent()
        }else{
            LazyColumn {
                items(
                    items = listOfTask.data,
                    key = { task -> task.id })
                { task ->
                    TaskItem(task = task, navigateToDoTask = navigateToDoTask)
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

@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = ToDoTask(id = 1, description = "", priority = Priority.Medium),
        navigateToDoTask = {})
}