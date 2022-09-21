package com.example.randomdog.data

import android.os.Build.VERSION_CODES.M
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randomdog.model.Dog
import com.example.randomdog.model.RandomDog
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.util.*
import javax.inject.Inject

class  DogRepository @Inject constructor(
    private val dogRemoteDataSource: DogRemoteDataSource,
    private val dogLocalDataSource: DogLocalDataSource
) {
    var lastRandomDog: RandomDog? = null

    val favouriteDogs = mutableListOf<Dog>()

    suspend fun getRandomDog(): RandomDog?{
        val res = dogRemoteDataSource.fetchRandomDog()
        if(res.isSuccessful){
            lastRandomDog = res.body()
            return lastRandomDog
        }
        throw IOException()
    }

    suspend fun saveDogUrl(dog : Dog){
        dogLocalDataSource.SaveUrlToDogDb(dog)

        favouriteDogs.clear()
        favouriteDogs.addAll(dogLocalDataSource.getAllDogsFromDB())
    }

    suspend fun getAllDogsFromDb(): MutableList<Dog>{
        favouriteDogs.clear()
        favouriteDogs.addAll(dogLocalDataSource.getAllDogsFromDB())
        return favouriteDogs
    }

}