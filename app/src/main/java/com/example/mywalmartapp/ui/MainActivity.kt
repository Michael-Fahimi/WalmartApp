package com.example.mywalmartapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mywalmartapp.databinding.ActivityMainBinding
import com.example.mywalmartapp.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountryViewModel by viewModels()
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        if (savedInstanceState == null) {
            viewModel.fetchCountries()
        }
    }

    private fun setupRecyclerView() {
        adapter = CountryAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            adapter.setCountries(countries)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}