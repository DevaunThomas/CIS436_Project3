package com.example.cis436_project3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// NOTE FOR TEAM:
// This ViewModel currently uses sample data.
// Replace this with API data from Dog API in the next phase.
// Keep the same LiveData structure.

// ViewModel stores UI data and survives screen rotation
class DogViewModel : ViewModel() {

    // Private mutable list of dog breeds
    private val _breeds = MutableLiveData<List<String>>()

    // Public read-only list for fragments to observe
    val breeds: LiveData<List<String>> = _breeds

    // Private mutable selected breed
    private val _selectedBreed = MutableLiveData<String>()

    // Public read-only selected breed
    val selectedBreed: LiveData<String> = _selectedBreed

    // Initialize with sample data
    init {
        _breeds.value = listOf(
            "Select a dog breed",
            "Labrador Retriever",
            "Beagle",
            "Poodle",
            "German Shepherd"
        )

        // No breed selected when app first opens
        _selectedBreed.value = ""
    }

    // Update the selected breed
    fun selectBreed(breed: String) {
        _selectedBreed.value = breed
    }
}