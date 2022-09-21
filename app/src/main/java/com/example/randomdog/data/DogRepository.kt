package com.example.randomdog.data

import com.example.randomdog.model.RandomDog
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.util.*
import javax.inject.Inject

class  DogRepository @Inject constructor(
    private val dogRemoteDataSource: DogRemoteDataSource,
    private val dogLocalDataSource: DogLocalDataSource
) {
    var lastRandomDog: RandomDog? = null

    val favouriteDogs = mutableListOf<String>()

    suspend fun getRandomDog(): RandomDog?{
        val res = dogRemoteDataSource.fetchRandomDog()
        if(res.isSuccessful){
            lastRandomDog = res.body()
            return lastRandomDog
        }
        throw IOException()
    }

    suspend fun saveDogUrl(url : String){
        favouriteDogs.add(url)
    }

}