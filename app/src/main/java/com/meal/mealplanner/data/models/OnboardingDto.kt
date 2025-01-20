package com.meal.mealplanner.data.models

import com.google.gson.annotations.SerializedName

data class FirebaseContractDto(
    @field:SerializedName("contract") val contract: String?
)

data class OnboardingDto(
    @field:SerializedName("onboarding_pages") val onboardingPages: List<OnboardingPageDto?>?
)

data class OnboardingPageDto(
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("button") val button: String?,
    @field:SerializedName("footer") val footer: String?,
    @field:SerializedName("title") val title: List<TextStyleDto?>?,
    @field:SerializedName("options") val options: OptionsDto?
)

data class TextStyleDto(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("text_color") val textColor: String?,
    @field:SerializedName("text_size") val textSize: Int?,
    @field:SerializedName("text_weight") val textWeight: String?,
    @field:SerializedName("highlighted") val highlighted: Boolean?
)

data class OptionsDto(
    @field:SerializedName("unselected") val unselected: BackgroundStyleDto?,
    @field:SerializedName("selected") val selected: BackgroundStyleDto?,
    @field:SerializedName("items") val items: List<OptionItemDto?>?
)

data class BackgroundStyleDto(
    @field:SerializedName("bg_color") val bgColor: String?,
    @field:SerializedName("stroke_color") val strokeColor: String?,
    @field:SerializedName("stroke_width") val strokeWidth: Int?
)

data class OptionItemDto(
    @field:SerializedName("title") val title: TextStyleDto?,
    @field:SerializedName("subtitle") val subtitle: TextStyleDto?,
    @field:SerializedName("icon") val icon: String?
)