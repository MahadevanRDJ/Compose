package com.example.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Screen
import com.example.compose.ui.theme.ComposeTheme


@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to all country",
            fontSize = 32.sp)
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { navController.navigate(Screen.Login.route) }) {
            Text("Start")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomeScreen() {
    ComposeTheme() {
        WelcomeScreen(navController = rememberNavController())
    }
}
