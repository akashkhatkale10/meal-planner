package com.meal.mealplanner.presentation.ui.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.meal.mealplanner.domain.models.BackgroundStyle
import com.meal.mealplanner.domain.models.OnboardingPage
import com.meal.mealplanner.domain.models.OnboardingQuestionModel
import com.meal.mealplanner.domain.models.OptionItem
import com.meal.mealplanner.presentation.models.QuestionClick
import com.meal.mealplanner.presentation.ui.bottomsheet.AgeBottomSheet
import com.meal.mealplanner.presentation.ui.bottomsheet.HeightBottomSheet
import com.meal.mealplanner.presentation.ui.bottomsheet.TargetWeightBottomSheet
import com.meal.mealplanner.presentation.ui.bottomsheet.WeightBottomSheet
import com.meal.mealplanner.presentation.ui.composables.CustomButton
import com.meal.mealplanner.presentation.ui.composables.CustomButtonState
import com.meal.mealplanner.presentation.ui.composables.StyleText
import com.meal.mealplanner.presentation.ui.modifiers.addBackgroundColor
import com.meal.mealplanner.presentation.ui.modifiers.bounceClick
import com.meal.mealplanner.presentation.viewmodels.OnboardingViewModel
import com.meal.mealplanner.ui.theme.AccentColor
import com.meal.mealplanner.ui.theme.CustomTextStyle
import com.meal.mealplanner.ui.theme.SubtitleColor
import com.meal.mealplanner.ui.theme.WhiteColor5


@Composable
fun OnboardingQ1Screen(
    viewModel: OnboardingViewModel,
    page: OnboardingPage?,
    modifier: Modifier = Modifier
) {
    var showBottomsheet by remember {
        mutableStateOf(false)
    }
    var selectedBottomsheet: OnboardingQuestionModel? by remember {
        mutableStateOf(null)
    }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column() {
            StyleText(
                copy = page?.title
            )

            Spacer(modifier = Modifier.height(30.dp))

            StyleText(
                copy = page?.subtitle
            )

            Spacer(modifier = Modifier.height(40.dp))

            page?.questions?.let { questions ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    questions.forEach { question ->
                        QuestionsComponent(
                            viewModel = viewModel,
                            question = question,
                            onClick = { questionType ->
                                when (questionType) {
                                    is QuestionClick.Selection -> {
                                        showBottomsheet = true
                                        selectedBottomsheet = question
                                    }

                                    is QuestionClick.Options -> {
                                        if (page.questions.size > 1) {
                                            viewModel.updateSingleAnswer(
                                                questionId = question.id,
                                                questionType = question.type,
                                                answerId = questionType.answerId,
                                                answer = question.question?.text.orEmpty()
                                            )
                                        } else {
                                            viewModel.updateAnswer(
                                                questionId = question.id,
                                                questionType = question.type,
                                                answerId = questionType.answerId,
                                                answer = question.question?.text.orEmpty()
                                            )
                                        }
                                    }

                                    is QuestionClick.TwoOptions -> {
                                        if (page.questions.size > 1) {
                                            viewModel.updateSingleAnswer(
                                                questionId = question.id,
                                                questionType = question.type,
                                                answerId = questionType.answerId,
                                                answer = question.question?.text.orEmpty()
                                            )
                                        } else {
                                            viewModel.updateAnswer(
                                                questionId = question.id,
                                                questionType = question.type,
                                                answerId = questionType.answerId,
                                                answer = question.question?.text.orEmpty()
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (page?.footer != null) {
                StyleText(
                    copy = listOf(page.footer),
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )
            }

            if (((page?.questions?.size ?: 0) > 1) || viewModel.containsQuestion(page = page)) {
                CustomButton(
                    text = page?.button.orEmpty(),
                    modifier = Modifier,
                    onClick = {
                        viewModel.goAhead()
                    },
                    state = if (viewModel.finalResult.containsList(page?.questions?.map { it.id }.orEmpty()))
                        CustomButtonState.Enabled else CustomButtonState.Disabled
                )
            }
        }
    }

    if (showBottomsheet) {
        when (selectedBottomsheet?.selectionType) {
            "age" -> {
                AgeBottomSheet(
                    onDismiss = {
                        showBottomsheet = false
                    },
                    onConfirm = { age ->
                        val suffix = "$age ${if (age == "1") "year" else "years"}"
                        viewModel.updateSingleAnswer(
                            questionId = selectedBottomsheet?.id.orEmpty(),
                            questionType = selectedBottomsheet?.type ?: OnboardingQuestionModel.OnboardingQuestionType.none,
                            answerId = "",
                            answer = age,
                            answerText = suffix
                        )
                        showBottomsheet = false
                    }
                )
            }

            "weight" -> {
                WeightBottomSheet(
                    onDismiss = {
                        showBottomsheet = false
                    },
                    onConfirm = { weight ->
                        val suffix = "$weight ${if (weight == "1") "kg" else "kgs"}"
                        viewModel.updateSingleAnswer(
                            questionId = selectedBottomsheet?.id.orEmpty(),
                            questionType = selectedBottomsheet?.type ?: OnboardingQuestionModel.OnboardingQuestionType.none,
                            answerId = "",
                            answer = weight,
                            answerText = suffix
                        )
                        showBottomsheet = false
                    }
                )
            }

            "height" -> {
                HeightBottomSheet(
                    onDismiss = {
                        showBottomsheet = false
                    },
                    onConfirm = { height ->
                        val suffix = "$height ${if (height == "1") "cm" else "cms"}"
                        viewModel.updateSingleAnswer(
                            questionId = selectedBottomsheet?.id.orEmpty(),
                            questionType = selectedBottomsheet?.type ?: OnboardingQuestionModel.OnboardingQuestionType.none,
                            answerId = "",
                            answer = height,
                            answerText = suffix
                        )
                        showBottomsheet = false
                    }
                )
            }

            "target_weight" -> {
                TargetWeightBottomSheet(
                    onDismiss = {
                        showBottomsheet = false
                    },
                    onConfirm = { weight ->
                        val suffix = "$weight ${if (weight == "1") "kg" else "kgs"}"
                        viewModel.updateSingleAnswer(
                            questionId = selectedBottomsheet?.id.orEmpty(),
                            questionType = selectedBottomsheet?.type ?: OnboardingQuestionModel.OnboardingQuestionType.none,
                            answerId = "",
                            answer = weight,
                            answerText = suffix
                        )
                        showBottomsheet = false
                    }
                )
            }
        }
    }
}

fun SnapshotStateMap<*,*>.containsList(list: List<String>): Boolean {
    var c = true
    list.forEach {
        c = c && contains(it)
    }

    return c
}

@Composable
fun QuestionsComponent(
    viewModel: OnboardingViewModel,
    question: OnboardingQuestionModel,
    onClick: (question: QuestionClick) -> Unit
) {
    when (question.type) {
        OnboardingQuestionModel.OnboardingQuestionType.options -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                question.question?.let {
                    StyleText(
                        copy = listOf(question.question)
                    )
                }
                question.options?.let { options ->
                    options.items?.forEach { option ->
                        OptionComponent(
                            item = option,
                            background = if (viewModel.finalResult.contains(question.id)
                                && viewModel.finalResult[question.id]?.answerId == option.answerId
                            )
                                options.selected else options.unselected,
                            onClick = {
                                onClick(QuestionClick.Options(option.answerId))
                            }
                        )
                    }
                }
            }
        }

        OnboardingQuestionModel.OnboardingQuestionType.two_options -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                question.question?.let {
                    StyleText(
                        copy = listOf(question.question)
                    )
                }
                question.options?.let { options ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(18.dp),
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                    ) {
                        options.items?.forEach { option ->
                            TwoOptionComponent(
                                item = option,
                                background = if (viewModel.finalResult.contains(question.id)
                                    && viewModel.finalResult[question.id]?.answerId == option.answerId
                                )
                                    options.selected else options.unselected,
                                onClick = {
                                    onClick(QuestionClick.TwoOptions(option.answerId))
                                }
                            )
                        }
                    }
                }
            }
        }

        OnboardingQuestionModel.OnboardingQuestionType.selection -> {
            Row(
                modifier = Modifier
                    .bounceClick {
                        onClick(QuestionClick.Selection(question.selectionType))
                    }
                    .background(
                        color = WhiteColor5,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                question.question?.let {
                    StyleText(
                        copy = listOf(question.question)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Text(
                        text = if (viewModel.finalResult.contains(question.id))
                            viewModel.finalResult[question.id]?.answerText.orEmpty() else "Select",
                        style = CustomTextStyle.copy(
                            color = AccentColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = AccentColor,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }
            }
        }

        else -> {

        }
    }
}

@Composable
fun TwoOptionComponent(
    item: OptionItem,
    background: BackgroundStyle?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .bounceClick {
                onClick()
            }
            .background(
                color = runCatching {
                    Color(
                        android.graphics.Color.parseColor(
                            background?.bgColor
                        )
                    )
                }.getOrElse { SubtitleColor },
                shape = RoundedCornerShape(10.dp),
            )
            .border(
                width = background?.strokeWidth?.dp ?: 1.dp,
                color = runCatching {
                    Color(
                        android.graphics.Color.parseColor(
                            background?.strokeColor
                        )
                    )
                }.getOrElse { SubtitleColor },
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.icon.orEmpty(),
            style = CustomTextStyle.copy(
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.padding(start = 10.dp))
        StyleText(
            copy = listOf(item.title)
        )
    }
}

@Composable
fun OptionComponent(
    item: OptionItem,
    background: BackgroundStyle?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .bounceClick {
                onClick()
            }
            .background(
                color = runCatching {
                    Color(
                        android.graphics.Color.parseColor(
                            background?.bgColor
                        )
                    )
                }.getOrElse { SubtitleColor },
                shape = RoundedCornerShape(10.dp),
            )
            .border(
                width = background?.strokeWidth?.dp ?: 1.dp,
                color = runCatching {
                    Color(
                        android.graphics.Color.parseColor(
                            background?.strokeColor
                        )
                    )
                }.getOrElse { SubtitleColor },
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 18.dp, vertical = 14.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.icon.orEmpty(),
            style = CustomTextStyle.copy(
                fontSize = 30.sp
            )
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            item.title?.let {
                StyleText(
                    copy = listOf(item.title)
                )
            }

            item.subtitle?.let {
                StyleText(
                    copy = listOf(item.subtitle),
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
            }
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
        OnboardingQ1Screen(
            page = null,
            viewModel = hiltViewModel()
        )
    }
}