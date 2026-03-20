package com.example.cis436_project3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Main entry point of the app
// It simply loads the main layout which contains the fragments
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout that contains the two fragments
        setContentView(R.layout.activity_main)
    }
}