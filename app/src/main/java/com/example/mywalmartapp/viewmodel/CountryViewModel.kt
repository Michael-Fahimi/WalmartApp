package com.example.mywalmartapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywalmartapp.data.Country
import com.example.mywalmartapp.repository.CountryRepository
import com.example.mywalmartapp.util.Result
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val repository = CountryRepository()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchCountries() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getCountries()) {
                is Result.Success -> {
                    _countries.value = result.data
                    _isLoading.value = false
                }
                is Result.Failure -> {
                    _error.value = result.exception.message
                    _isLoading.value = false
                }
            }
        }
    }
}