package com.example.compose.countrydetail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.countrylist.ListCountryViewModel
import com.example.compose.ui.theme.ComposeTheme

//val CountrySaver = listSaver(
//    save = { listOf(it.name, it.currencies, it.capital, it.area, it.population) },
//    restore = { Country(it[0] as Name, it[1] as Map<String, Currency>, it[2] as List<String>, it[3] as Double, it[4] as Long) }
//)
@Composable
fun CountryDetail(
    countryListViewModel: ListCountryViewModel,
    country: String,
    navController: NavHostController
) {

    var data = countryListViewModel.getCountryDetail(country)
    val currencyData: StringBuilder = countryListViewModel.getCurrency()
    Log.d("Currency", currencyData.toString())

    if(data == null) navController.navigateUp()
    CountryDetailScreen(data, currencyData, navController, country)
}

@Preview(showBackground = true)
@Composable
fun CountryDetailPreview() {
    ComposeTheme {
        CountryDetail(ListCountryViewModel(), "", navController = rememberNavController())
    }
}