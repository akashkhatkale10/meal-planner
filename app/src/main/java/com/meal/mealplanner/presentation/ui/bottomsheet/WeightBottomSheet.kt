package com.meal.mealplanner.presentation.ui.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.meal.mealplanner.presentation.ui.composables.CustomButton
import com.meal.mealplanner.presentation.ui.composables.CustomButtonState
import com.meal.mealplanner.presentation.ui.composables.CustomTextField
import com.meal.mealplanner.ui.theme.CustomTextStyle

@Composable
fun WeightBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onConfirm: (age: String) -> Unit = {}
) {
    var value by remember {
        mutableStateOf("")
    }

    CustomBottomSheet(
        modifier = modifier,
        content = {
            Column (
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            ) {
                CustomTextField(
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    placeholder = "Enter your weight (kg)",
                    placeholderStyle = CustomTextStyle.copy(
                        color = Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(80.dp)
                )

                CustomButton(
                    text = "Confirm",
                    onClick = {
                        onConfirm(value)
                    },
                    state = if (value.isEmpty() || value == "0") CustomButtonState.Disabled else CustomButtonState.Enabled
                )
            }
        },
        onDismiss = onDismiss
    )
}