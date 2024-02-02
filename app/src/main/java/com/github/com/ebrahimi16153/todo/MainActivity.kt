package com.github.com.ebrahimi16153.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.com.ebrahimi16153.todo.navigation.Navigation
import com.github.com.ebrahimi16153.todo.ui.theme.ToDoTheme
import com.github.com.ebrahimi16153.todo.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    private lateinit var navController: NavHostController
    private val shearedViewModel:SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ToDoTheme {
                navController = rememberNavController()
                Navigation(navController = navController ,shearedViewModel = shearedViewModel)

            }
        }
    }
}

