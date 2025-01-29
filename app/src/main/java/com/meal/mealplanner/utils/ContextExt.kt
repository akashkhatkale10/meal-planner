package com.meal.mealplanner.utils

import android.content.Context

inline fun<reified T> Context.toModelFromAssets(jsonFileNameFromAssets: String): T? {
    val json = assets?.open(jsonFileNameFromAssets)?.bufferedReader().use {
        it?.readText()
    }
    return toModel(json, T::class.java)
}