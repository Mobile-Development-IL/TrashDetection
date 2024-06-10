package com.infinitelearning.trashdetection.data.repository

import com.infinitelearning.trashdetection.data.network.NetworkDataSource
import com.infinitelearning.trashdetection.data.network.response.DetectResponse
import com.infinitelearning.trashdetection.domain.DetectRepository
import com.infinitelearning.trashdetection.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetectRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : DetectRepository {

    override fun uploadImage(image: File): Flow<Result<DetectResponse>> = flow {
        emit(Result.Loading())
        try {
            val response = networkDataSource.uploadImage(image)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}