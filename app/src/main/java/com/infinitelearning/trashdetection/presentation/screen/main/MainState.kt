package com.infinitelearning.trashdetection.presentation.screen.main

import com.infinitelearning.trashdetection.data.network.response.DetectResponse

data class MainState(
    val error: String? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val isBottomSheetShown: Boolean = false,
    val result: DetectResponse? = null
)