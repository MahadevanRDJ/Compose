package com.example.compose.data.repository

import com.example.compose.data.dao.RegisterDao
import com.example.compose.data.entity.RegisterEntity

class RegisterRepository(private val registerDao: RegisterDao) {

    suspend fun insert(user: RegisterEntity) {
        registerDao.insert(user)
    }

    fun getUser(userName: String, passWord: String): RegisterEntity? {
        var user: RegisterEntity? = try {
            registerDao.getUser(userName, passWord)
        } catch (e: Exception) {
            null
        }
        return user
    }
}