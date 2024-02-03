package com.github.com.ebrahimi16153.todo.navigation.destinations

import android.util.Log
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.screens.task.TaskScreen
import com.github.com.ebrahimi16153.todo.util.Constants.TASK_SCREEN
import com.github.com.ebrahimi16153.todo.util.Constants.TASK_SCREEN_ARGUMENT_KEY
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit

) {

    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(name = TASK_SCREEN_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->

        val taskId = navBackStackEntry.arguments!!.getInt(TASK_SCREEN_ARGUMENT_KEY)

        TaskScreen(
            navigateToList = navigateToListScreen,
            sharedViewModel = sharedViewModel,
            taskId = taskId
        )


    }


}