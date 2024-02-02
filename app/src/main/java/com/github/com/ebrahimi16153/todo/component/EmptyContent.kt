package com.github.com.ebrahimi16153.todo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.R

@Composable
fun EmptyContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.sdd_face),
            contentDescription = "No Content here",
            tint = MaterialTheme.colorScheme.onBackground
        )
        Text(text = "No Task For Today!", color = MaterialTheme.colorScheme.onBackground)


    }


}


@Composable
@Preview
fun PreviewEmptyContent() {

    EmptyContent()

}