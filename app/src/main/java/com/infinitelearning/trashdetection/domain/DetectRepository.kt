package com.infinitelearning.trashdetection.domain

import com.infinitelearning.trashdetection.data.network.response.DetectResponse
import com.infinitelearning.trashdetection.utils.Result
import kotlinx.coroutines.flow.Flow
import java.io.File

interface DetectRepository {
    fun uploadImage(image: File): Flow<Result<DetectResponse>>
}