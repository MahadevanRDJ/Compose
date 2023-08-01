package com.example.compose.countrydetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose.Screen


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountryDetailScreen(
    data: String,
    currencyData: StringBuilder,
    navController: NavHostController,
    country: String
) {
    Surface(color = MaterialTheme.colorScheme.inverseSurface) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = country, textAlign = TextAlign.Center) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Screen.CountryList.route) }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            },
        ) {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = data,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = currencyData.toString(),
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}
