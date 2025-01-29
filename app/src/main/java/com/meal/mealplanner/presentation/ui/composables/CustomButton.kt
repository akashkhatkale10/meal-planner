package com.meal.mealplanner.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meal.mealplanner.presentation.ui.modifiers.bounceClick
import com.meal.mealplanner.ui.theme.AccentColor
import com.meal.mealplanner.ui.theme.CustomTextStyle
import com.meal.mealplanner.ui.theme.WhiteColor

sealed class CustomButtonState {
    data object Enabled: CustomButtonState()
    data object Disabled: CustomButtonState()
}

@Composable
fun CustomButton(
    text: String,
    state: CustomButtonState = CustomButtonState.Enabled,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .bounceClick(
                enabled = state == CustomButtonState.Enabled
            ) {
                if (state == CustomButtonState.Disabled) return@bounceClick

                onClick()
            }
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = if (state == CustomButtonState.Enabled) AccentColor else Color.Gray.copy(alpha = 0.5f)
            )
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {
        Text(
            text = text,
            style = CustomTextStyle.copy(
                color = WhiteColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier =  Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun CustomButtonPreview(modifier: Modifier = Modifier) {
    CustomButton(
        text = "Continue"
    )
}