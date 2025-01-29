package com.meal.mealplanner.presentation.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import com.meal.mealplanner.domain.models.TextStyle
import com.meal.mealplanner.ui.theme.CustomTextStyle
import com.meal.mealplanner.ui.theme.SubtitleColor
import com.meal.mealplanner.ui.theme.WhiteColor
import com.meal.mealplanner.utils.getFontWeight

@Composable
fun StyleText(
    modifier: Modifier = Modifier,
    copy: List<TextStyle?>?,
    delimiter: String? = null,
    concatenator: String? = null,
) {
    copy?.let {
        val string = buildAnnotatedString {
            it.fastForEachIndexed { index, item ->
                item?.let {
                    withStyle(
                        style = SpanStyle(
                            color = runCatching { Color(
                                android.graphics.Color.parseColor(
                                    item.textColor
                                )
                            ) }.getOrElse { WhiteColor },
                            fontWeight = getFontWeight(item.textWeight),
                            fontSize = item.textSize.sp,
                        )
                    ) {
                        append(concatenator?.let { "$it " } ?: "")
                        append(item.text)
                        if (index < copy.size - 1) {
                            delimiter?.let { append(it) }
                        }
                    }
                }
            }
        }
        Text(
            text = string,
            style = CustomTextStyle,
            modifier = modifier
        )
    }
}