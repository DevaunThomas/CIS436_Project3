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
import com.example.cis436_project3.model.DogBreed
import com.example.cis436_project3.viewmodel.DogViewModel

// This fragment displays the Spinner for selecting a dog breed
class BreedSelectorFragment : Fragment() {

    private lateinit var breedSpinner: Spinner
    private val viewModel: DogViewModel by activityViewModels()

    // Stores full breed objects locally
    private var breeds: List<DogBreed> = emptyList()

    // Prevent automatic first selection
    private var isFirstSelection = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breed_selector, container, false)

        breedSpinner = view.findViewById(R.id.breedSpinner)

        // Observe full breed list from ViewModel
        viewModel.breedList.observe(viewLifecycleOwner) { breedList ->
            breeds = breedList

            val spinnerItems = mutableListOf("Select a dog breed")
            spinnerItems.addAll(breedList.map { it.name })

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                spinnerItems
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            breedSpinner.adapter = adapter
        }

        // Handle user selection
        breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                // Ignore first automatic trigger
                if (isFirstSelection) {
                    isFirstSelection = false
                    return
                }

                // Ignore default item
                if (position == 0) {
                    return
                }

                // Position - 1 because Spinner has default item first
                val selectedBreed = breeds[position - 1]
                viewModel.selectBreed(selectedBreed)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        return view
    }
}