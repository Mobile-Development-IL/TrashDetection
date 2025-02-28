package com.infinitelearning.trashdetection.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.infinitelearning.trashdetection.ui.theme.TrashDetectionExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrashDetectionExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TrashDetectionApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}