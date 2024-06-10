package com.infinitelearning.trashdetection.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infinitelearning.trashdetection.presentation.screen.main.MainScreen

@Composable
fun TrashDetectionApp(
    modifier: Modifier = Modifier,
) {
    MainScreen(
        modifier = modifier
    )
}