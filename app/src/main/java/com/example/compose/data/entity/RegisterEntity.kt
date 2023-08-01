package com.example.compose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class RegisterEntity(

    @ColumnInfo(name = "user_name")
    val userName : String,

    @ColumnInfo(name = "first_name")
    val firstName : String,

    @ColumnInfo(name = "last_name")
    val lastName : String,

    @ColumnInfo(name = "password")
    val password : String,

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
)
