package com.github.com.ebrahimi16153.todo.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.data.Priority

@Composable
fun PriorityCircle(priority: Priority) {


    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            shape = CircleShape,
            modifier = Modifier.size(15.dp),
            content = {},
            color = priority.color
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = priority.name)
    }


}



@Composable
@Preview
fun PreviewPriorityCircle(){
    PriorityCircle(priority = Priority.High)
}

