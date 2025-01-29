package com.meal.mealplanner.data.repository

import android.content.Context
import com.meal.mealplanner.config.RemoteConfig
import com.meal.mealplanner.config.RemoteConfig.Companion.ONBOARDING_API
import com.meal.mealplanner.data.mappers.toOnboardingModel
import com.meal.mealplanner.data.models.OnboardingDto
import com.meal.mealplanner.domain.models.OnboardingModel
import com.meal.mealplanner.domain.repository.OnboardingRepository
import com.meal.mealplanner.utils.toModel
import com.meal.mealplanner.utils.toModelFromAssets
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val remoteConfig: RemoteConfig,
    @ApplicationContext private val context: Context
): OnboardingRepository {

    override suspend fun getOnboardingPage(): Result<OnboardingModel?> {
        try {
//            val mock = context.toModelFromAssets<OnboardingDto>("sample.json")
//            return Result.success(mock!!.toOnboardingModel())
            val data = toModel(remoteConfig.getString(ONBOARDING_API), OnboardingDto::class.java)
            return Result.success(data?.toOnboardingModel())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}