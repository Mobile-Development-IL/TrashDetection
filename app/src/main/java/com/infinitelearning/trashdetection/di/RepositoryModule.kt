package com.infinitelearning.trashdetection.di

import com.infinitelearning.trashdetection.data.repository.DetectRepositoryImpl
import com.infinitelearning.trashdetection.domain.DetectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDetectRepository(detectRepositoryImpl: DetectRepositoryImpl): DetectRepository
}