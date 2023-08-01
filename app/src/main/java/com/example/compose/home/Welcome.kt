package com.example.compose.home

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun Welcome(
    navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.primaryContainer
    ) {
        WelcomeScreen(navController = navController)
    }
}

private fun isDeviceOnline(context: Context): Boolean {
    val connManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
    return networkCapabilities != null
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    ComposeTheme {
        Welcome()
    }
}