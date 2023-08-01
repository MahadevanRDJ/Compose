package com.example.compose.countrylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun CountryList(
    countryListviewModel: ListCountryViewModel,
    navController: NavHostController,
) {
    val countryList by rememberSaveable {
        mutableStateOf(countryListviewModel.getCountryList())
    }
    CountryListScreen(countryList, navController)
}

@Composable
fun DisplayProgressBar() {
    Surface(color = MaterialTheme.colorScheme.onPrimary) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(10.dp),
                trackColor = Color.LightGray,
                color = Color.Green
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryPreview() {
    ComposeTheme {
        CountryList(ListCountryViewModel(), navController = rememberNavController())
    }
}