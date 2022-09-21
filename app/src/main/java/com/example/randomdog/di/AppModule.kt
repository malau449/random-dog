package com.example.randomdog.di

import android.content.Context
import androidx.room.Room
import com.example.randomdog.RandomDogApplication
import com.example.randomdog.model.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): RandomDogApplication = app as RandomDogApplication

    @Singleton
    @Provides
    fun provideDogDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, DogDatabase::class.java, "dog_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDogDao(db: DogDatabase) = db.getDogDao()
}