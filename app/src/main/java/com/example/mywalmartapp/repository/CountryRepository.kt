package com.example.mywalmartapp.repository

import com.example.mywalmartapp.data.Country
import com.example.mywalmartapp.network.RetrofitClient
import com.example.mywalmartapp.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class CountryRepository {
    suspend fun getCountries(): Result<List<Country>> {
        return withContext(Dispatchers.IO) {
            try {
                val countries = RetrofitClient.apiService.getCountries()
                Result.Success(countries)
            } catch (e: IOException) {
                Result.Failure(Exception("Network error: ${e.message}"))
            } catch (e: Exception) {
                Result.Failure(Exception("Unexpected error: ${e.message}"))
            }
        }
    }
}
