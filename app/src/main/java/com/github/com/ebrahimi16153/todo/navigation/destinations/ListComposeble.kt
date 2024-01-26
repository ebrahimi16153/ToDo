package com.github.com.ebrahimi16153.todo.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.com.ebrahimi16153.todo.screens.list.ListScreen
import com.github.com.ebrahimi16153.todo.util.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(Int) -> Unit
) {


    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(name = Constants.LIST_SCREEN_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {

         ListScreen(navigateToTask = navigateToTaskScreen)

    }
}