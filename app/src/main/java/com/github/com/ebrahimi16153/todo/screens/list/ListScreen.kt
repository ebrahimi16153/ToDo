package com.github.com.ebrahimi16153.todo.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.github.com.ebrahimi16153.todo.R
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTask: (taskId: Int) -> Unit,
    shearedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true ){
        shearedViewModel.getAllTask()
    }

    // first way to define val
    val searchTextState = remember {
        shearedViewModel.searchTextState
    }

    val searchBarState = remember {
        shearedViewModel.searchBarState
    }

    // list of all task
    val tasks by shearedViewModel.allTask.collectAsState()

    // second way to define val
//    val searchTextState :String by shearedViewModel.searchTextState
//    val searchBarState :SearchBarState by shearedViewModel.searchBarState

    Scaffold(topBar = {

        ListAppBar(
            shearedViewModel = shearedViewModel,
            onSearchClick = {},
            onSortClick = {},
            onDeleteAll = {},
            searchBarState = searchBarState.value,
            textState = searchTextState.value
        )


    }, floatingActionButton = { FabButton(navigateToTask = navigateToTask) }){

      Column( modifier = Modifier
          .fillMaxSize()
          .padding(it)
      ) {
             ListContent(navigateToDoTask = navigateToTask, listOfTask = tasks)
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

