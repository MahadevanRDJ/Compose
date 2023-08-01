package com.example.compose.countrylist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.compose.data.dto.Country

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController, countryList: List<Country>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search")},
                modifier = Modifier.clickable {  } ,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icons.Outlined.ArrowBack
                    }
                }
            )
        }
    ) {
        Text(text = "Search")
    }
}