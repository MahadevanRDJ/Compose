package com.example.compose.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.compose.data.database.CountryDatabase
import com.example.compose.data.repository.RegisterRepository

@Composable
fun Login(navController: NavHostController) {
    val dao = CountryDatabase.getInstance(LocalContext.current).registerDao()
    val repository = RegisterRepository(dao)
    val loginViewModel  = LoginViewModel(repository)
    LoginScreen(loginViewModel, navController)
}