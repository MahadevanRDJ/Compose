package com.example.compose.countrylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.dto.Country
import com.example.compose.network.RestCountryAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.StringBuilder

@HiltViewModel
class ListCountryViewModel : ViewModel() {
    private var countryList = mutableListOf<Country>()
    private var currency = StringBuilder()

    fun getCountryList() = countryList.sortedBy { it.name.common }

    init {
        getCountryListProperties()
    }

    private fun getCountryListProperties() {
        viewModelScope.launch(Dispatchers.IO) {
            var getCountryListDeferred = RestCountryAPI.retrofitService.getCountryList()
            try {
                countryList = getCountryListDeferred.await()
                Log.d("Country", "$countryList")
            } catch (e : Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }

    fun getCountryDetail(country : String) : String {
        currency.clear()
        var country = countryList.find { it.name.common == country}
        if (country != null) {
            for (i in country.currencies) {
                currency.append(" ${i.value.name} and it's symbol is ${i.value.symbol} \n")
            }
        }
        return "The capital of ${country?.name?.common} is ${country?.capital?.get(0)}, which is also the largest city in the country. \n" + "The area of ${country?.name?.common} is ${country?.area} square kilometers. \n" + "The population of ${country?.name?.common} is ${country?.population} people.\n" + "Currency : "
    }

    fun getCurrency() = currency
    fun getChartData(): List<Country> {
        return countryList.sortedByDescending { it.population }.subList(0, 10)
    }
}