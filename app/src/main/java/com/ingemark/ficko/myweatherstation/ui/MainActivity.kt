package com.ingemark.ficko.myweatherstation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ingemark.ficko.myweatherstation.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupScreen()
    }

    fun buttonClicked() {
        // check if input field is not empty
        // fetch data from api based on user input
    }

    private fun setupScreen() {
        // fetch data for any location
        binding.activity = this
    }
}