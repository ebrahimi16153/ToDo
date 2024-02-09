package com.github.com.ebrahimi16153.todo.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MyAlertDialog(
    openDialog: Boolean,
    title: String,
    message: String,
    closeDialog: () -> Unit,
    onYseClicked: () -> Unit

) {

    if (openDialog) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.background,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            },

            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            },

            confirmButton = {
                Button(
                    onClick = {
                        onYseClicked()
                        closeDialog()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                        MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "OK", color = MaterialTheme.colorScheme.onPrimary)
                }
            },

            dismissButton = {

                OutlinedButton(
                    onClick = { closeDialog() },
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "No" , color = MaterialTheme.colorScheme.onBackground)
                }

            },

            onDismissRequest = { closeDialog() })
    }


}