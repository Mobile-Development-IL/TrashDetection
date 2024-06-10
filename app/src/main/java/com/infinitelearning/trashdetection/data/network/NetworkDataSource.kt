package com.infinitelearning.trashdetection.data.network

import com.infinitelearning.trashdetection.data.network.response.DetectResponse
import com.infinitelearning.trashdetection.data.network.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun uploadImage(image: File): DetectResponse {
        val requestImageFile = image.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultiPart = MultipartBody.Part.createFormData(
            "file",
            image.name,
            requestImageFile
        )
        return apiService.uploadImage(imageMultiPart)
    }
}