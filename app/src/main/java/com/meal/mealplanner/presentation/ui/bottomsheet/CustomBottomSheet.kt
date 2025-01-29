package com.meal.mealplanner.presentation.ui.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.meal.mealplanner.ui.theme.Gradient3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Gradient3
    ) {
        content()
    }
}