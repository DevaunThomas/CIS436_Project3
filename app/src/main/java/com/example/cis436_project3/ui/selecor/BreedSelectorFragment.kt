package com.example.cis436_project3.ui.selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cis436_project3.R
import com.example.cis436_project3.viewmodel.DogViewModel

// This fragment displays the Spinner (dropdown) for selecting a dog breed
class BreedSelectorFragment : Fragment() {

    // Spinner UI element
    private lateinit var breedSpinner: Spinner

    // Shared ViewModel (used by both fragments)
    private val viewModel: DogViewModel by activityViewModels()

    // Flag to prevent automatic selection when app starts
    private var isFirstSelection = true

    // This function creates the UI for the fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Load the layout XML
        val view = inflater.inflate(R.layout.fragment_breed_selector, container, false)

        // Connect Spinner from XML
        breedSpinner = view.findViewById(R.id.breedSpinner)

        // Observe breed list from ViewModel
        viewModel.breeds.observe(viewLifecycleOwner) { breedList ->

            // Create adapter to connect data to Spinner
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                breedList
            )

            // Set dropdown style
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Attach adapter to Spinner
            breedSpinner.adapter = adapter
        }

        // Handle user selection
        breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                // Ignore the first automatic call when app starts
                if (isFirstSelection) {
                    isFirstSelection = false
                    return
                }

                // Get selected breed
                val selected = parent.getItemAtPosition(position).toString()

                // Ignore the default option
                if (position != 0) {
                    // Update ViewModel with selected breed
                    viewModel.selectBreed(selected)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Return the view to display
        return view
    }
}