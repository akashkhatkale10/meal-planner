package com.meal.mealplanner.config.di

import com.meal.mealplanner.config.RemoteConfig
import com.meal.mealplanner.config.RemoteConfigImpl
import com.meal.mealplanner.data.repository.OnboardingRepositoryImpl
import com.meal.mealplanner.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigModule {

    @Binds
    abstract fun bindRemoteConfig(
        impl: RemoteConfigImpl
    ): RemoteConfig
}