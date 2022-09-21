package com.example.randomdog.data

import com.example.randomdog.di.IoDispatcher
import com.example.randomdog.model.Dog
import com.example.randomdog.model.DogDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogLocalDataSource @Inject constructor(
    private val dogDao: DogDao,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) {
    suspend fun SaveUrlToDogDb(dog: Dog){
        withContext(ioDispatcher){
            dogDao.insert(dog)
        }
    }

    suspend fun getAllDogsFromDB(): List<Dog>{
        return withContext(ioDispatcher){
            dogDao.getAllDogs()
        }
    }
}