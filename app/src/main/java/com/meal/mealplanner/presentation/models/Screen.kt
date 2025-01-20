package com.meal.mealplanner.presentation.models

sealed class Screen(val route: String) {
    data object OnboardingScreen: Screen("onboarding_screen")
}