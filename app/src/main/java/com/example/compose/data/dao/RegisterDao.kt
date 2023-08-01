package com.example.compose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.compose.data.entity.RegisterEntity

@Dao
interface RegisterDao {
    @Insert
    suspend fun insert(register : RegisterEntity)

    @Query("SELECT * from users where user_name = :userName AND password = :passWord")
    fun getUser(userName: String, passWord: String) : RegisterEntity

}
