package com.github.com.ebrahimi16153.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.com.ebrahimi16153.todo.navigation.destinations.listComposable
import com.github.com.ebrahimi16153.todo.navigation.destinations.taskComposable
import com.github.com.ebrahimi16153.todo.util.Constants.LIST_SCREEN

@Composable
fun Navigation(navController: NavHostController) {

    val screen = remember(navController){
        Screens(navController = navController)
    }
    NavHost(navController = navController, startDestination = LIST_SCREEN ){

        listComposable(screen.task)

        taskComposable(screen.list)

    }

}