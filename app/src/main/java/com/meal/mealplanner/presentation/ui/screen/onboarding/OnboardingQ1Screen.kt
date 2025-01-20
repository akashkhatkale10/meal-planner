package com.meal.mealplanner.presentation.ui.screen.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.meal.mealplanner.presentation.ui.modifiers.addBackgroundColor
import com.meal.mealplanner.ui.theme.CustomTextStyle
import com.meal.mealplanner.ui.theme.WhiteColor

@Composable
fun OnboardingQ1Screen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            Text(
                text = "What do you want to do ?",
                style = CustomTextStyle.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = WhiteColor
                )
            )
        }

        item {

        }
    }
}

@Composable
@Preview
fun OnboardingQ1ScreenPreview(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .addBackgroundColor()
            .fillMaxSize()
    ) {
        OnboardingQ1Screen()
    }
}