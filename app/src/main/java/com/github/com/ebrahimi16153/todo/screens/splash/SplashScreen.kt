package com.github.com.ebrahimi16153.todo.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.com.ebrahimi16153.todo.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navigateToList: () -> Unit) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val offsetState = animateDpAsState(
        targetValue = if (startAnimation) {
            0.dp
        } else {
            100.dp
        },
        label = "", animationSpec = tween(durationMillis = 1000)
    )

    val alfaState = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        label = "",
        animationSpec = tween(durationMillis = 1000)
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navigateToList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier.offset(y = offsetState.value).alpha(alfaState.value),
            painter = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.logo_dark else R.drawable.logo_light
            ), contentDescription = ""
        )
    }
}