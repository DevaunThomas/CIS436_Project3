package com.example.cis436_project3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cis436_project3.data.DogApiService
import com.example.cis436_project3.model.DogBreed

// This ViewModel stores dog breed data for the UI
class DogViewModel(application: Application) : AndroidViewModel(application) {

    // API service used to fetch breed data
    private val dogApiService = DogApiService(application)

    // Full list of breeds
    private val _breedList = MutableLiveData<List<DogBreed>>()
    val breedList: LiveData<List<DogBreed>> = _breedList

    // Selected breed object
    private val _selectedBreed = MutableLiveData<DogBreed?>()
    val selectedBreed: LiveData<DogBreed?> = _selectedBreed

    init {
        // No breed selected at first
        _selectedBreed.value = null

        // Load real breed data
        loadBreeds()
    }

    // Load breed list from API
    private fun loadBreeds() {
        dogApiService.fetchBreeds(
            onSuccess = { breeds ->
                _breedList.postValue(breeds)
            },
            onError = {
                _breedList.postValue(emptyList())
            }
        )
    }

    // Store selected breed object
    fun selectBreed(breed: DogBreed) {
        _selectedBreed.value = breed
    }
}