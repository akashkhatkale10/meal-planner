package com.meal.mealplanner.domain.repository

import com.meal.mealplanner.domain.models.OnboardingModel

interface OnboardingRepository {
    suspend fun getOnboardingPage(): Result<OnboardingModel?>
}