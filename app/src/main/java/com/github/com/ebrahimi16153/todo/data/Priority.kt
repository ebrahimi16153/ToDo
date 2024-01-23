package com.github.com.ebrahimi16153.todo.data

import androidx.compose.ui.graphics.Color
import com.github.com.ebrahimi16153.todo.ui.theme.*

enum class Priority(val color: Color) {

    High(HighPriority),
    Medium(MediumPriority),
    Low(LowPriority),
    None(NonePriority)


}