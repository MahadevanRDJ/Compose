package com.example.compose.data.dto


data class Country (
    val flags: Flag,
    val name : Name,
    val currencies: Map<String, Currency>,
    val capital : List<String>,
    val area : Double,
    val population : Long
)


data class Flag (
    val png : String,
    val svg : String,
    val alt : String
)