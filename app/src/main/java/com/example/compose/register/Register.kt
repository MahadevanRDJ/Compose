package com.example.compose.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose.data.database.CountryDatabase
import com.example.compose.data.repository.RegisterRepository

@Composable
fun Register(navController: NavHostController) {
    val dao = CountryDatabase.getInstance(LocalContext.current).registerDao()
    val repository = RegisterRepository(dao)
    val registerViewModel : RegisterViewModel = viewModel(factory = RegisterViewModelFactory(repository))
    RegisterScreen(registerViewModel, navController)
}


