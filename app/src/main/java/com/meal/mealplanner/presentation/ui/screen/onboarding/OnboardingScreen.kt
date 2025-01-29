package com.meal.mealplanner.presentation.ui.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.meal.mealplanner.presentation.ui.modifiers.addBackgroundColor
import com.meal.mealplanner.presentation.ui.modifiers.addHorizontalPadding
import com.meal.mealplanner.presentation.ui.modifiers.bounceClick
import com.meal.mealplanner.presentation.viewmodels.OnboardingViewModel
import com.meal.mealplanner.ui.theme.CustomTextStyle
import com.meal.mealplanner.ui.theme.WhiteColor
import com.meal.mealplanner.ui.theme.WhiteColor10

@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<OnboardingViewModel>()
    val state by viewModel.uiState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.getOnboarding()
    }

    Box(
        modifier = modifier
            .addBackgroundColor()
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.onboardingData?.let { data ->
            Column {
                Row(
                    modifier = Modifier
                        .addHorizontalPadding(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    (0 until data.onboardingPages.size).forEachIndexed { index, i ->
                        Box(
                            modifier = Modifier
                                .height(4.dp)
                                .background(
                                    color = if (state.currentPage == index) WhiteColor else WhiteColor10,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .weight(1f)
                        )
                    }
                }

                if (state.currentPage > 0) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = WhiteColor,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .size(50.dp)
                            .padding(10.dp)
                            .bounceClick {
                                viewModel.goBack()
                            }
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))
                OnboardingQ1Screen(
                    viewModel = viewModel,
                    page = data.onboardingPages[state.currentPage],
                    modifier = Modifier
                        .addHorizontalPadding()
                )
            }
        }
        
        if (state.error != null) {
            Text(
                text = "Something went wrong",
                style = CustomTextStyle.copy(
                    color = WhiteColor
                )
            )
        }
    }
}

@Composable
@Preview
fun OnboardingScreenPreview(modifier: Modifier = Modifier) {
    OnboardingScreen(
        navController = rememberNavController()
    )
}