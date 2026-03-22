package com.example.cis436_project3.model

// This data class stores full information about a dog breed
data class DogBreed(
    val id: Int,
    val name: String,
    val temperament: String,
    val origin: String,
    val lifeSpan: String,
    val bredFor: String,
    val imageUrl: String
)