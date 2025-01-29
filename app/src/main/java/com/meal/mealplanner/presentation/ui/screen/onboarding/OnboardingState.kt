package com.meal.mealplanner.presentation.ui.screen.onboarding

import com.meal.mealplanner.domain.models.OnboardingModel

data class OnboardingState(
    val onboardingData: OnboardingModel? = null,
    val isLoading: Boolean,
    val error: Throwable? = null,
    val currentPage: Int
)
