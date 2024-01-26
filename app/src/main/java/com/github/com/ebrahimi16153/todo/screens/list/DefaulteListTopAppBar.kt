package com.github.com.ebrahimi16153.todo.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.R
import com.github.com.ebrahimi16153.todo.component.PriorityCircle
import com.github.com.ebrahimi16153.todo.data.Priority
import com.github.com.ebrahimi16153.todo.ui.theme.myError


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaulterListTopAppBar(

    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteAll: () -> Unit


) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        title = { Text(text = "Tasks", color = MaterialTheme.colorScheme.onPrimary) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = {
             Action(onSearchClick = onSearchClick, onSortClick = onSortClick,onDeleteAll = onDeleteAll)
        })
}


@Composable
fun Action(
    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteAll: () -> Unit
) {
    Search(onSearchClick = onSearchClick)
    Spacer(modifier = Modifier.width(5.dp))
    SortList(onSortClick = onSortClick)
    Spacer(modifier = Modifier.width(5.dp))
    DeleteAll(onDeleteAll = onDeleteAll)

}


@Composable
fun Search(onSearchClick: () -> Unit) {

    IconButton(onClick = { onSearchClick() }) {

        Icon(

            imageVector = Icons.Default.Search,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )

    }

}


@Composable
fun SortList(onSortClick: (Priority) -> Unit) {


    var expend by remember { mutableStateOf(false) }
    IconButton(onClick = { expend = true }) {

        Icon(

            painter = painterResource(id = R.drawable.filter),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )

    }

    DropdownMenu(expanded = expend, onDismissRequest = { expend = false }) {

        DropdownMenuItem(text = {
            PriorityCircle(priority = Priority.High)


        }, onClick = {
            expend = false
            onSortClick(Priority.High)
        })

        DropdownMenuItem(text = {
            PriorityCircle(priority = Priority.Medium)
        },
            onClick = {
                expend = false
                onSortClick(Priority.Medium)
            })


        DropdownMenuItem(text = {
            PriorityCircle(priority = Priority.Low)


        }, onClick = {
            expend = false
            onSortClick(Priority.Low)
        })
    }

}


@Composable
fun DeleteAll(onDeleteAll: () -> Unit) {

    var expend by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expend = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )

    }

    DropdownMenu(expanded = expend, onDismissRequest = { expend = false }) {

        DropdownMenuItem(text = {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Delete All")
                Spacer(modifier = Modifier.width(5.dp))

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.myError
                )

            }

        }, onClick = {
            expend = false
            onDeleteAll()
        })


    }


}

@Composable
@Preview
fun PreviewListTopAppBar() {
    Action(onSearchClick = { /*TODO*/ }, onSortClick = {}, onDeleteAll = {})
        

}