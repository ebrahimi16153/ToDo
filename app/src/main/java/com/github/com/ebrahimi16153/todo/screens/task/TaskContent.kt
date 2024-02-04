package com.github.com.ebrahimi16153.todo.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.component.PriorityDropDown
import com.github.com.ebrahimi16153.todo.data.Priority


@Composable
fun TaskContent(
    title:String,
    onTitleChange:(String) -> Unit,
    priority: Priority,
    onPrioritySelected:(Priority) ->Unit,
    description:String,
    onDescriptionChange:(String) -> Unit
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(15.dp)) {
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value =title , onValueChange = onTitleChange, label = { Text(text = "Title")}, singleLine = true )
        Spacer(modifier = Modifier.height(8.dp))
        PriorityDropDown(priority = priority, onPrioritySelected = onPrioritySelected )
//        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(modifier = Modifier.fillMaxSize(), value =description , onValueChange = onDescriptionChange, label = { Text(text = "Description")} )


    }
}


@Composable
@Preview
fun PreviewTaskContent(){
    TaskContent(
        title = "",
        onTitleChange = {} ,
        priority = Priority.High,
        onPrioritySelected ={} ,
        description ="" ,
        onDescriptionChange ={}
    )
}
