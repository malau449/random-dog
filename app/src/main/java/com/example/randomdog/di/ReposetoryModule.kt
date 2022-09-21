package com.example.randomdog.di

import com.example.randomdog.data.DogLocalDataSource
import com.example.randomdog.data.DogRemoteDataSource
import com.example.randomdog.data.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReposetoryModule {

    @Singleton
    @Provides
    fun provideDogReposetory(dogRemoteDataSource: DogRemoteDataSource, dogLocalDataSource: DogLocalDataSource): DogRepository = DogRepository(dogRemoteDataSource, dogLocalDataSource)
}