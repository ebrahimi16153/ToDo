package com.github.com.ebrahimi16153.todo.navigation

import androidx.navigation.NavHostController
import com.github.com.ebrahimi16153.todo.util.Constants.LIST_SCREEN
import com.github.com.ebrahimi16153.todo.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {


    // handel splash

    val splash:()-> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}"){
            popUpTo(SPLASH_SCREEN){ inclusive = true}
        }
    }


     // handel navigate to listScreen
    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }

    }

    // handel navigate to TaskScreen
    val task:(taskId:Int) -> Unit = {taskID ->
        navController.navigate(route = "task/$taskID")
    }


}