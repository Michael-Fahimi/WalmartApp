package com.example.mywalmartapp.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mywalmartapp.data.Country
import com.example.mywalmartapp.databinding.ItemCountryBinding


class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private var countries: List<Country> = emptyList()

    fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.textNameRegion.text = "${country.name}, ${country.region}"
            binding.textCode.text = country.code
            binding.textCapital.text = country.capital
        }
    }
}