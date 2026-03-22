package com.example.cis436_project3.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.cis436_project3.model.DogBreed

// This class handles all API requests to The Dog API
class DogApiService(context: Context) {

    // Volley queue for network requests
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    // Endpoint for loading all dog breeds
    private val breedsUrl = "https://api.thedogapi.com/v1/breeds"

    // Put your real API key here
    private val apiKey = "live_lUhh1UayWK5xNNqLCWDviyRmzzEz0f0lT9dPmu5jaH6r2gfAIc1iGqf6cN9393R9\n"

    // This function fetches full breed data from the API
    fun fetchBreeds(
        onSuccess: (List<DogBreed>) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = object : JsonArrayRequest(
            Request.Method.GET,
            breedsUrl,
            null,

            // Runs if request succeeds
            { response ->
                val breedList = mutableListOf<DogBreed>()

                // Read each breed from the JSON response
                for (i in 0 until response.length()) {
                    val breedObject = response.getJSONObject(i)

                    val id = breedObject.optInt("id", 0)
                    val name = breedObject.optString("name", "Unknown")
                    val temperament = breedObject.optString("temperament", "Not available")
                    val origin = breedObject.optString("origin", "Unknown")
                    val lifeSpan = breedObject.optString("life_span", "Not available")
                    val bredFor = breedObject.optString("bred_for", "Not available")

                    // Image object may or may not exist
                    val imageObject = breedObject.optJSONObject("image")
                    val imageUrl = imageObject?.optString("url", "") ?: ""

                    val breed = DogBreed(
                        id = id,
                        name = name,
                        temperament = temperament,
                        origin = origin,
                        lifeSpan = lifeSpan,
                        bredFor = bredFor,
                        imageUrl = imageUrl
                    )

                    breedList.add(breed)
                }

                onSuccess(breedList)
            },

            // Runs if request fails
            { error ->
                val statusCode = error.networkResponse?.statusCode

                val errorMessage = when (statusCode) {
                    401 -> "Unauthorized - check API key"
                    403 -> "Forbidden - invalid API key"
                    404 -> "Endpoint not found"
                    else -> error.message ?: "Failed to load dog breeds"
                }

                onError(errorMessage)
            }
        ) {
            // Add API key in request header
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(
                    "x-api-key" to apiKey
                )
            }
        }

        // Send request
        requestQueue.add(request)
    }
}