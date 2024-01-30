package com.github.com.ebrahimi16153.todo.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.github.com.ebrahimi16153.todo.R
import com.github.com.ebrahimi16153.todo.util.SearchBarState
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTask: (taskId: Int) -> Unit,
    shearedViewModel: SharedViewModel
) {

    // first way to define val

    val searchTextState = remember {
        shearedViewModel.searchTextState
    }

    val searchBarState = remember {
        shearedViewModel.searchBarState
    }

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


    }, content = {}, floatingActionButton = { FabButton(navigateToTask = navigateToTask) })


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

