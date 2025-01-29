package com.meal.mealplanner.presentation.models

sealed class QuestionClick {

    data class Options(
        val answerId: String,
    ): QuestionClick()

    data class TwoOptions(
        val answerId: String,
    ): QuestionClick()

    data class Selection(
        val selectionType: String
    ): QuestionClick()
}