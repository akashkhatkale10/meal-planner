package com.meal.mealplanner.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.meal.mealplanner.config.RemoteConfig
import com.meal.mealplanner.config.RemoteConfig.Companion.ONBOARDING_API
import com.meal.mealplanner.data.mappers.toOnboardingModel
import com.meal.mealplanner.data.models.OnboardingDto
import com.meal.mealplanner.domain.models.OnboardingModel
import com.meal.mealplanner.domain.repository.OnboardingRepository
import com.meal.mealplanner.utils.toModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.log

class OnboardingRepositoryImpl @Inject constructor(
    private val remoteConfig: RemoteConfig
): OnboardingRepository {

    override suspend fun getOnboardingPage(): Result<OnboardingModel?> {
        try {
            val data = toModel(remoteConfig.getString(ONBOARDING_API), OnboardingDto::class.java)
            return Result.success(data?.toOnboardingModel())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}