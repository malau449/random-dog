package com.example.randomdog.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdog.data.DogRepository
import com.example.randomdog.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FavouriteDogsViewModel @Inject constructor(private val dogRepository : DogRepository) : ViewModel(){

    private val _favouriteDogsList = MutableLiveData<List<Dog>>()
    public val favouriteDogsList : LiveData<List<Dog>>
        get() = _favouriteDogsList

    var fetchJob: Job? = null

    init {
        FetchFavouriteDogs()
    }

    fun FetchFavouriteDogs(){
        fetchJob = viewModelScope.launch{
            try{
                _favouriteDogsList.value = dogRepository.getAllDogsFromDb()
            } catch (ioe: IOException){

            }
        }
    }
}