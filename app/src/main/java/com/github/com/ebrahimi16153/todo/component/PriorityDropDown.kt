package com.github.com.ebrahimi16153.todo.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.data.Priority


@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    // handel animation of ArrowDownDrop
    val angle: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                expanded = true
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Canvas(modifier = Modifier
            .size(16.dp)
            .weight(0.1f), onDraw = { drawCircle(color = priority.color) })
        Text(
            modifier = Modifier.weight(0.8f),
            text = "Priority",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            modifier = Modifier
                .rotate(angle)
                .weight(0.15f),
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary.copy( alpha = 0.5f)
        )


        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {

            DropdownMenuItem(text = {
                PriorityCircle(priority = Priority.Low)
            }, onClick = {
                onPrioritySelected(Priority.Low)
                expanded = false
            })


            DropdownMenuItem(text = {
                PriorityCircle(priority = Priority.Medium)
            }, onClick = {
                onPrioritySelected(Priority.Medium)
                expanded = false
            })

            DropdownMenuItem(text = {
                PriorityCircle(priority = Priority.High)
            }, onClick = {
                onPrioritySelected(Priority.High)
                expanded = false
            })

        }

    }


}


@Composable
@Preview
fun PreviewPriorityDropDown() {
    PriorityDropDown(priority = Priority.Low, onPrioritySelected = {})
}