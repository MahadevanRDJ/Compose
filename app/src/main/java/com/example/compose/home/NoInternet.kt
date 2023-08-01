package com.example.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NoInternetConnectivity(modifier : Modifier = Modifier) {
    Surface(color = Color.Red) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No \nInternet \nConnectivity!!!",
                fontSize = 34.sp,
                color = Color.White,
                lineHeight = 60.sp,
                fontWeight = FontWeight(1000),
                fontStyle = FontStyle.Italic,
            )
        }
    }
}