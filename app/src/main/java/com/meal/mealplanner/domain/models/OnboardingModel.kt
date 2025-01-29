package com.meal.mealplanner.domain.models

import com.meal.mealplanner.data.models.OptionsDto


data class OnboardingModel(
    val onboardingPages: List<OnboardingPage>
)

data class OnboardingPage(
    val type: String,
    val button: String?,
    val footer: TextStyle?,
    val title: List<TextStyle>?,
    val subtitle: List<TextStyle>?,
    val questions: List<OnboardingQuestionModel>?,
)

data class OnboardingQuestionModel(
    val id: String,
    val selectionType: String,
    val question: TextStyle?,
    val type: OnboardingQuestionType,
    val options: Options?
) {
    enum class OnboardingQuestionType {
        options, two_options, selection, none;

        companion object {
            fun find(value: String?) = entries.find { it.name == value } ?: none
        }
    }
}

data class TextStyle(
    val text: String,
    val textColor: String,
    val textSize: Int,
    val textWeight: String,
    val highlighted: Boolean
)

data class Options(
    val unselected: BackgroundStyle?,
    val selected: BackgroundStyle?,
    val items: List<OptionItem>?
)

data class BackgroundStyle(
    val bgColor: String,
    val strokeColor: String,
    val strokeWidth: Int
)

data class OptionItem(
    val answerId: String,
    val title: TextStyle?,
    val subtitle: TextStyle?,
    val icon: String?
)