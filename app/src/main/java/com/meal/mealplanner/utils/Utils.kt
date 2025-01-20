package com.meal.mealplanner.utils

import android.util.Log
import com.google.gson.Gson

fun <T> toModel(value: String?, classOf: Class<T>): T? {
    return try {
        Gson().fromJson(value, classOf)
    } catch (e: Exception) {
        Log.d("AKASH_LOG", "toModel: ${e}")
        null
    }
}

fun fromModel(value: Map<*, *>?): String? {
    return try {
        Gson().toJson(value)
    } catch (e: Exception) {
        null
    }
}