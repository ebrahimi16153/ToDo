package com.github.com.ebrahimi16153.todo.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.com.ebrahimi16153.todo.navigation.toAction
import com.github.com.ebrahimi16153.todo.screens.list.ListScreen
import com.github.com.ebrahimi16153.todo.util.Constants
import com.github.com.ebrahimi16153.todo.util.Constants.LIST_SCREEN_ARGUMENT_KEY
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    shearedViewModel: SharedViewModel
) {


    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(name = Constants.LIST_SCREEN_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_SCREEN_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            shearedViewModel.action.value = action
        }


        ListScreen(
            navigateToTask = navigateToTaskScreen,
            shearedViewModel = shearedViewModel
        )

    }
}