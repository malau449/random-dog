package com.example.randomdog.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdog.data.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RandomDogViewModel @Inject constructor(private val dogRepository : DogRepository) : ViewModel() {

    private val _randomDogUrl = MutableLiveData<String?>()
    val randomDogUrl: LiveData<String?>
        get() = _randomDogUrl

    private val _isFavouriteDog = MutableLiveData<Boolean>()
    val isFavouriteDog: LiveData<Boolean>
        get() = _isFavouriteDog

    private var fetchJob: Job? = null

    init {
        FetchRandomDog()
    }

    fun FetchRandomDog(){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch{
            try{
                val randomDog = dogRepository.getRandomDog()
                _randomDogUrl.value = randomDog?.message
                _isFavouriteDog.value = false
            } catch (ioe: IOException){

            }
        }
    }

    fun favouriteDog(){
        //add url to db
        viewModelScope.launch {
            try{
                if(randomDogUrl.value != null)
                dogRepository.saveDogUrl(randomDogUrl.value.toString())
                _isFavouriteDog.value = true
            } catch(exception: Exception){
                throw exception
            }
        }
    }
}