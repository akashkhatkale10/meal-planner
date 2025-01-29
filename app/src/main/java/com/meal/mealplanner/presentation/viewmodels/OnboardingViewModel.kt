package com.meal.mealplanner.presentation.viewmodels

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meal.mealplanner.domain.models.OnboardingPage
import com.meal.mealplanner.domain.models.OnboardingQuestionModel
import com.meal.mealplanner.domain.repository.OnboardingRepository
import com.meal.mealplanner.presentation.ui.screen.onboarding.OnboardingState
import com.meal.mealplanner.presentation.ui.screen.onboarding.QuestionAnswer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<OnboardingState> = MutableStateFlow(OnboardingState(
        isLoading = true,
        currentPage = 0
    ))
    val uiState: StateFlow<OnboardingState> = _uiState.asStateFlow()

    val finalResult: SnapshotStateMap<String, QuestionAnswer> = mutableStateMapOf()

    fun getOnboarding() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        onboardingRepository.getOnboardingPage().onSuccess { data ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    onboardingData = data
                )
            }
        }.onFailure { err ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = err,
                    onboardingData = null
                )
            }
        }
    }

    fun updateAnswer(
        questionId: String,
        questionType: OnboardingQuestionModel.OnboardingQuestionType,
        answerId: String,
        answer: String,
        answerText: String = ""
    ) = viewModelScope.launch {
        if (finalResult.contains(questionId)) {
            // show continue button
            finalResult[questionId] = QuestionAnswer(
                type = questionType.name,
                questionId = questionId,
                answerId = answerId,
                answer = answer,
                answerText = answerText,
                showButton = true
            )
        } else {
            finalResult[questionId] = QuestionAnswer(
                type = questionType.name,
                questionId = questionId,
                answerId = answerId,
                answer = answer,
                answerText = answerText,
                showButton = false
            )
            delay(500)
            finalResult[questionId] = finalResult[questionId]!!.copy(
                showButton = true
            )
            _uiState.update {
                it.copy(
                    currentPage = (it.currentPage + 1).coerceAtMost(maximumValue = (_uiState.value.onboardingData?.onboardingPages?.size ?: 1) - 1)
                )
            }
        }
    }

    fun updateSingleAnswer(
        questionId: String,
        questionType: OnboardingQuestionModel.OnboardingQuestionType,
        answerId: String,
        answer: String,
        answerText: String = ""
    ) = viewModelScope.launch {
        finalResult[questionId] = QuestionAnswer(
            type = questionType.name,
            questionId = questionId,
            answerId = answerId,
            answer = answer,
            answerText = answerText
        )
    }

    fun goBack() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                currentPage = (it.currentPage - 1).coerceAtLeast(0)
            )
        }
    }

    fun goAhead() = viewModelScope.launch {
        delay(500)
        _uiState.update {
            it.copy(
                currentPage = (it.currentPage + 1).coerceAtMost(maximumValue = (_uiState.value.onboardingData?.onboardingPages?.size ?: 1) - 1)
            )
        }
    }

    fun containsQuestion(page: OnboardingPage?): Boolean {
        var contains = true
        page?.questions?.forEach {
            contains = contains && finalResult.contains(it.id) && finalResult[it.id]!!.showButton
        }

        return contains
    }
}