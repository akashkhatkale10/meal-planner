package com.meal.mealplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meal.mealplanner.presentation.models.Screen
import com.meal.mealplanner.presentation.ui.screen.onboarding.OnboardingScreen
import com.meal.mealplanner.presentation.viewmodels.OnboardingViewModel
import com.meal.mealplanner.ui.theme.MealPlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealPlannerTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.OnboardingScreen.route) {
                    composable(route = Screen.OnboardingScreen.route) {
                        OnboardingScreen(navController = navController)
                    }
                }
            }
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.accent)
    }
}