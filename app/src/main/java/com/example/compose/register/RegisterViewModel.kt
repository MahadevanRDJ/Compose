package com.example.compose.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.data.entity.RegisterEntity
import com.example.compose.data.repository.RegisterRepository


class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    suspend fun addUser(user : RegisterEntity) {
        registerRepository.insert(user)
    }

}

class RegisterViewModelFactory(
    private val repository: RegisterRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}