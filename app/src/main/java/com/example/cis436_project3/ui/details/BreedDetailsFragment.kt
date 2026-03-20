package com.example.cis436_project3.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cis436_project3.R
import com.example.cis436_project3.viewmodel.DogViewModel

// This fragment displays the selected dog's information
class BreedDetailsFragment : Fragment() {

    // Shared ViewModel used with the selector fragment
    private val viewModel: DogViewModel by activityViewModels()

    // TextViews for showing dog information
    private lateinit var nameTextView: TextView
    private lateinit var temperamentTextView: TextView
    private lateinit var originTextView: TextView
    private lateinit var lifeSpanTextView: TextView
    private lateinit var bredForTextView: TextView

    // This function creates the UI for the fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Load the layout XML file
        val view = inflater.inflate(R.layout.fragment_breed_details, container, false)

        // Connect XML views to Kotlin code
        nameTextView = view.findViewById(R.id.nameTextView)
        temperamentTextView = view.findViewById(R.id.temperamentTextView)
        originTextView = view.findViewById(R.id.originTextView)
        lifeSpanTextView = view.findViewById(R.id.lifeSpanTextView)
        bredForTextView = view.findViewById(R.id.bredForTextView)

        // Observe the selected breed from the shared ViewModel
        viewModel.selectedBreed.observe(viewLifecycleOwner) { breed ->

            // If no real breed is selected yet, show empty placeholders
            if (breed.isNotEmpty() && breed != "Select a dog breed") {
                nameTextView.text = "Name: $breed"
                temperamentTextView.text = "Temperament: Friendly, Active"
                originTextView.text = "Origin: Unknown"
                lifeSpanTextView.text = "Life Span: 10 - 12 years"
                bredForTextView.text = "Bred For: Herding"
            } else {
                nameTextView.text = "Name: -"
                temperamentTextView.text = "Temperament: -"
                originTextView.text = "Origin: -"
                lifeSpanTextView.text = "Life Span: -"
                bredForTextView.text = "Bred For: -"
            }
        }

        return view
    }
}