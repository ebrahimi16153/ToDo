package com.github.com.ebrahimi16153.todo.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.com.ebrahimi16153.todo.navigation.Action


@Composable
fun TaskScreen(
//    sharedViewModel:SharedViewModel,
    navigateToList:(Action) -> Unit
) {

    Scaffold (topBar = { TaskAppBar(navigateToList = navigateToList)}){

        Column(modifier = Modifier.fillMaxSize().padding(it)) {

        }

    }

}