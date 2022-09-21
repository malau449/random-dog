package com.example.randomdog.ui.main

import androidx.lifecycle.ViewModel
import com.example.randomdog.data.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteDogsViewModel @Inject constructor(private val dogRepository : DogRepository) : ViewModel(){



    init {
        FetchFavouriteDogs()
    }

    fun FetchFavouriteDogs(): List<String>{
        return dogRepository.favouriteDogs.toList()
    }
}