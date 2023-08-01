package com.example.compose

import android.content.Context
import android.net.ConnectivityManager
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.chart.Chart
import com.example.compose.countrydetail.CountryDetail
import com.example.compose.countrylist.CountryList
import com.example.compose.home.Welcome
import com.example.compose.countrylist.ListCountryViewModel
import com.example.compose.home.NoInternetConnectivity
import com.example.compose.login.Login
import com.example.compose.register.Register


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CountryList : Screen("country_list")
    object CountryDetail : Screen("{country}/country_detail") {
        fun createRoute(country: String) = "${country}/country_detail"
    }
    object Chart : Screen("chart")
    object Login : Screen("login")
    object Register : Screen("register")
}

@Composable
fun CountryNavHost(
) {
    val context = LocalContext.current
    val navController: NavHostController = rememberNavController()
    val countryListViewModel = ListCountryViewModel()
    var isOnline by rememberSaveable {
        mutableStateOf(isDeviceOnline(context))
    }
    if (isOnline) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
        ) {

            composable(route = Screen.Home.route) {
                Welcome(navController)
            }
            composable(route = Screen.Login.route) {

                Login(navController = navController)
            }
            composable(route = Screen.Register.route) {
                Register(navController = navController)
            }
            composable(route = Screen.CountryList.route) {
                CountryList(countryListViewModel, navController)
            }
            composable(route = Screen.CountryDetail.route) { it ->
                val country = it.arguments?.getString("country")
                CountryDetail(countryListViewModel, country as String, navController)
            }
            composable(route = Screen.Chart.route) {
                Chart(countryListViewModel,navController)
            }
        }
        val backPressedCallback = rememberBackPressedCallback(navController)
        DisposableEffect(key1 = backPressedCallback)  {
            val onBackPressedDispatcher = MainActivity().onBackPressedDispatcher
            onBackPressedDispatcher.addCallback(backPressedCallback)
            onDispose {
                backPressedCallback.remove()
            }
        }
    } else {
        NoInternetConnectivity()
    }

}

@Composable
fun rememberBackPressedCallback(navController: NavHostController): OnBackPressedCallback {
    return remember {
        object  : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(navController.currentBackStackEntry?.destination?.route == Screen.Login.route) {
                    isEnabled = false
                    MainActivity().finish()
                } else {
                    isEnabled = true
                    navController.popBackStack()
                }
            }

        }
    }
}
private fun isDeviceOnline(context: Context): Boolean {
    val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
    return networkCapabilities != null
}