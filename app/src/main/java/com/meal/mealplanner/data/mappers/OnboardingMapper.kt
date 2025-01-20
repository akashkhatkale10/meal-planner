package com.meal.mealplanner.data.mappers

import com.meal.mealplanner.data.models.BackgroundStyleDto
import com.meal.mealplanner.data.models.OnboardingDto
import com.meal.mealplanner.data.models.OnboardingPageDto
import com.meal.mealplanner.data.models.OptionItemDto
import com.meal.mealplanner.data.models.OptionsDto
import com.meal.mealplanner.data.models.TextStyleDto
import com.meal.mealplanner.domain.models.BackgroundStyle
import com.meal.mealplanner.domain.models.OnboardingModel
import com.meal.mealplanner.domain.models.OnboardingPage
import com.meal.mealplanner.domain.models.OptionItem
import com.meal.mealplanner.domain.models.Options
import com.meal.mealplanner.domain.models.TextStyle

fun OnboardingDto.toOnboardingModel() = OnboardingModel(
    onboardingPages = this.onboardingPages?.mapNotNull {
        it.toOnboardingPage()
    }.orEmpty()
)

fun OnboardingPageDto?.toOnboardingPage() = if (this != null) {
    OnboardingPage(
        type = this.type.orEmpty(),
        title = this.title?.mapNotNull {
            it.toTextStyle()
        },
        footer = this.footer,
        options = this.options.toOptions(),
        button = this.button,
    )
} else null

fun TextStyleDto?.toTextStyle() = if (this != null) {
    TextStyle(
        text = this.text.orEmpty(),
        textWeight = this.textWeight.orEmpty(),
        textSize = this.textSize ?: 0,
        textColor = this.textColor.orEmpty(),
        highlighted = this.highlighted ?: false
    )
} else null

fun OptionsDto?.toOptions() = if (this != null) {
    Options(
        unselected = this.unselected?.toBackground(),
        selected = this.selected?.toBackground(),
        items = this.items?.mapNotNull {
            it.toOptionItem()
        }
    )
} else null

fun BackgroundStyleDto?.toBackground() = if (this != null) {
    BackgroundStyle(
        bgColor = this.bgColor.orEmpty(),
        strokeColor = this.strokeColor.orEmpty(),
        strokeWidth = this.strokeWidth ?: 0
    )
} else null

fun OptionItemDto?.toOptionItem() = if (this != null) {
    OptionItem(
        title = this.title?.toTextStyle(),
        subtitle = this.subtitle?.toTextStyle(),
        icon = this.icon
    )
} else null
