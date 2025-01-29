package com.meal.mealplanner.presentation.ui.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.meal.mealplanner.ui.theme.Gradient1
import com.meal.mealplanner.ui.theme.Gradient2

fun Modifier.addBackgroundColor() = Modifier
    .background(
        brush = Brush.verticalGradient(
            colors = listOf(
                Gradient1,
                Gradient2
            )
        )
    )

fun Modifier.addHorizontalPadding() = Modifier
    .padding(
        horizontal = 24.dp
    )

enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick(
    scaleDown: Float = 0.93f,
    enabled: Boolean = true,
    onClick: () -> Unit,
) = composed {
    if (enabled.not()) return@composed Modifier

    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) scaleDown else 1f)
    val haptic = LocalHapticFeedback.current

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}
