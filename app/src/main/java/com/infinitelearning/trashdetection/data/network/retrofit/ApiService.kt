package com.infinitelearning.trashdetection.data.network.retrofit

import com.infinitelearning.trashdetection.data.network.response.DetectResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("result")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): DetectResponse
}