package com.meal.mealplanner.config

interface RemoteConfig {
    companion object {
        const val ONBOARDING_API = "onboarding_api"
    }

    fun getString(key: String): String
}