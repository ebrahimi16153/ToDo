package com.github.com.ebrahimi16153.todo.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.com.ebrahimi16153.todo.navigation.Action
import com.github.com.ebrahimi16153.todo.util.Constants.TASK_SCREEN
import com.github.com.ebrahimi16153.todo.util.Constants.TASK_SCREEN_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit

) {

    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(name = TASK_SCREEN_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

        // TODO:TASK SCREEN
    }


}