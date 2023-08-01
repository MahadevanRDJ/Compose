package com.example.compose.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.data.entity.RegisterEntity
import com.example.compose.data.repository.RegisterRepository
import com.example.compose.data.util.UserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(private val registerRepository: RegisterRepository) : ViewModel() {
    private var user: RegisterEntity? = null

    fun checkUser(userName: String, passWord: String): Boolean {
        runBlocking {
            launch(Dispatchers.IO) {
                user = registerRepository.getUser(userName, passWord)
            }
        }
        return user != null && validate(user!!)
    }

    private fun validate(user: RegisterEntity): Boolean {
        return UserUtils.validateUserName(user.userName) && UserUtils.validatePassWord(user.password)
    }
}

class LogInViewModelFactory(
    private val repository: RegisterRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}