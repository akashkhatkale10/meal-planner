package com.meal.mealplanner.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.meal.mealplanner.ui.theme.WhiteColor

@Composable
fun CustomTextField(
    value: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    textStyle: TextStyle = TextStyle.Default,
    placeholder: String = "",
    placeholderStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (value: String) -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WhiteColor, shape = RoundedCornerShape(10.dp))
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .height(50.dp)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = placeholderStyle,
                    modifier = Modifier
                )
            }
            it()
        }
    }
}