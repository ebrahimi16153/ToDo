package com.github.com.ebrahimi16153.todo.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.com.ebrahimi16153.todo.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTask: (taskId: Int) -> Unit
) {

    Scaffold(topBar = {
        DefaulterListTopAppBar(
            onSearchClick = {},
            onSortClick = {},
            onDeleteAll = {})
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

@Composable
@Preview
fun ListScreenPreview() {
    ListScreen(navigateToTask = {

    })
}
