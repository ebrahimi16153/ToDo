package com.github.com.ebrahimi16153.todo.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.com.ebrahimi16153.todo.screens.splash.SplashScreen
import com.github.com.ebrahimi16153.todo.util.Constants.SPLASH_SCREEN


fun NavGraphBuilder.SplashComposable(
    navigateToList:() -> Unit
){

    composable(
        route = SPLASH_SCREEN
    ){

      SplashScreen(navigateToList = navigateToList)



    }


}

