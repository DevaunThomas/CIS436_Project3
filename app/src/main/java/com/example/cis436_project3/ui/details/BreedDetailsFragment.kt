package com.example.cis436_project3.ui.details

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cis436_project3.R
import com.example.cis436_project3.viewmodel.DogViewModel
import java.net.URL
import kotlin.concurrent.thread

// This fragment displays the selected dog's information
class BreedDetailsFragment : Fragment() {

    // Shared ViewModel
    private val viewModel: DogViewModel by activityViewModels()

    private lateinit var dogImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var temperamentTextView: TextView
    private lateinit var originTextView: TextView
    private lateinit var lifeSpanTextView: TextView
    private lateinit var bredForTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_breed_details, container, false)

        dogImageView = view.findViewById(R.id.dogImageView)
        nameTextView = view.findViewById(R.id.nameTextView)
        temperamentTextView = view.findViewById(R.id.temperamentTextView)
        originTextView = view.findViewById(R.id.originTextView)
        lifeSpanTextView = view.findViewById(R.id.lifeSpanTextView)
        bredForTextView = view.findViewById(R.id.bredForTextView)

        viewModel.selectedBreed.observe(viewLifecycleOwner) { breed ->
            if (breed != null) {
                val originText = if (breed.origin.isBlank() || breed.origin == "null") {
                    "Not available"
                } else {
                    breed.origin
                }

                val bredForText = if (breed.bredFor.isBlank() || breed.bredFor == "null") {
                    "Not available"
                } else {
                    breed.bredFor
                }

                nameTextView.text = "Name: ${breed.name}"
                temperamentTextView.text = "Temperament: ${breed.temperament}"
                originTextView.text = "Origin: $originText"
                lifeSpanTextView.text = "Life Span: ${breed.lifeSpan}"
                bredForTextView.text = "Bred For: $bredForText"

                if (breed.imageUrl.isNotEmpty()) {
                    loadImageFromUrl(breed.imageUrl)
                } else {
                    dogImageView.setImageResource(R.mipmap.ic_launcher)
                }
            } else {
                nameTextView.text = "Name: -"
                temperamentTextView.text = "Temperament: -"
                originTextView.text = "Origin: -"
                lifeSpanTextView.text = "Life Span: -"
                bredForTextView.text = "Bred For: -"
                dogImageView.setImageResource(R.mipmap.ic_launcher)
            }
        }

        return view
    }

    // Load image from URL in background thread
    private fun loadImageFromUrl(imageUrl: String) {
        thread {
            try {
                val inputStream = URL(imageUrl).openStream()
                val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

                activity?.runOnUiThread {
                    dogImageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                activity?.runOnUiThread {
                    dogImageView.setImageResource(R.mipmap.ic_launcher)
                }
            }
        }
    }
}