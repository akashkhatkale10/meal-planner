package com.meal.mealplanner.presentation.ui.modifiers

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.meal.mealplanner.ui.theme.Gradient1
import com.meal.mealplanner.ui.theme.Gradient2

fun Modifier.addBackgroundColor() = Modifier
    .background(
        brush = Brush.verticalGradient(
            colors = listOf(
                Gradient1,
                Gradient2
            )
        )
    )
