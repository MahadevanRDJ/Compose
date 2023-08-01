package com.example.compose.data.repository

import com.example.compose.data.dao.RegisterDao
import com.example.compose.data.entity.RegisterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

class RegisterRepository(private val registerDao: RegisterDao) {

    suspend fun insert(user: RegisterEntity) {
        registerDao.insert(user)
    }

    fun getUser(userName: String, passWord: String): RegisterEntity? {
        var user: RegisterEntity? = null
        user = try {
            registerDao.getUser(userName, passWord)
        } catch (e: Exception) {
            null
        }
        return user
    }
}