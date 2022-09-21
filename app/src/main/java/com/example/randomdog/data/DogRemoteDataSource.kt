package com.example.randomdog.data

import com.example.randomdog.di.IoDispatcher
import com.example.randomdog.model.RandomDog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

class DogRemoteDataSource @Inject constructor(
    private val dogApi : DogApi,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) {
    suspend fun fetchRandomDog() : Response<RandomDog> {
        return withContext(ioDispatcher){
            dogApi.fetchRandomDog()
        }
    }
}

interface DogApi {
    @GET("/api/breeds/image/random")
    suspend fun fetchRandomDog() : Response<RandomDog>
}