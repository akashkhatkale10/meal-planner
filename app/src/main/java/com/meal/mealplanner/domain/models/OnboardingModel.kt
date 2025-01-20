package com.meal.mealplanner.domain.models



data class OnboardingModel(
    val onboardingPages: List<OnboardingPage>
)

data class OnboardingPage(
    val type: String,
    val button: String?,
    val footer: String?,
    val title: List<TextStyle>?,
    val options: Options?
)

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
    val title: TextStyle?,
    val subtitle: TextStyle?,
    val icon: String?
)