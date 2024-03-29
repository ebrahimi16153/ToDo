package com.github.com.ebrahimi16153.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.com.ebrahimi16153.todo.navigation.destinations.SplashComposable
import com.github.com.ebrahimi16153.todo.navigation.destinations.listComposable
import com.github.com.ebrahimi16153.todo.navigation.destinations.taskComposable
import com.github.com.ebrahimi16153.todo.util.Constants.SPLASH_SCREEN
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel

@Composable
fun Navigation(navController: NavHostController, shearedViewModel: SharedViewModel) {

    val screen = remember(navController){
        Screens(navController = navController)
    }
    NavHost(navController = navController, startDestination = SPLASH_SCREEN ){

        listComposable(navigateToTaskScreen = screen.task, shearedViewModel = shearedViewModel)

        taskComposable(navigateToListScreen = screen.list , sharedViewModel = shearedViewModel)

        SplashComposable (navigateToList = screen.splash)

    }

}