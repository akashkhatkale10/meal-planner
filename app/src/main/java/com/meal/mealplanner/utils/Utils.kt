package com.meal.mealplanner.utils

import android.util.Log
import androidx.compose.ui.text.font.FontWeight
import com.google.gson.Gson

const val FONT_WEIGHT_MEDIUM = "medium"
const val FONT_WEIGHT_NORMAL = "regular"
const val FONT_WEIGHT_SEMIBOLD = "semibold"
const val FONT_WEIGHT_BOLD = "bold"

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

fun getFontWeight(
    weight: String
): FontWeight {
    return when (weight) {
        FONT_WEIGHT_BOLD -> FontWeight.Bold
        FONT_WEIGHT_MEDIUM -> FontWeight.Medium
        FONT_WEIGHT_NORMAL -> FontWeight.Normal
        FONT_WEIGHT_SEMIBOLD -> FontWeight.SemiBold
        else -> FontWeight.Medium
    }
}