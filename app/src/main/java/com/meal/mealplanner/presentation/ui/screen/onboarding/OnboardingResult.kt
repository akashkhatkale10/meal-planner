package com.meal.mealplanner.presentation.ui.screen.onboarding


data class QuestionAnswer(
    val type: String,
    val questionId: String,
    val answerId: String,
    val answer: String,
    val answerText: String = "",
    val showButton: Boolean = false
)