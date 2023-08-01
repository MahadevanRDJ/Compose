package com.example.compose.data.util

object UserUtils {
    fun validateUserName(userName : String) : Boolean {
        return Regex("^[a-zA-Z0-9]+\$").containsMatchIn(userName)
    }
    fun validatePassWord(passWord : String) : Boolean {
        return Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=_!*(){}]).{8,}\$").containsMatchIn(passWord)
    }
}