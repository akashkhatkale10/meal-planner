package com.meal.mealplanner.presentation.ui.screen.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.meal.mealplanner.presentation.ui.modifiers.addBackgroundColor

@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .addBackgroundColor()
            .fillMaxSize()
    ) {
        OnboardingQ1Screen()
    }
}

@Composable
@Preview
fun OnboardingScreenPreview(modifier: Modifier = Modifier) {
    OnboardingScreen(
        navController = rememberNavController()
    )
}