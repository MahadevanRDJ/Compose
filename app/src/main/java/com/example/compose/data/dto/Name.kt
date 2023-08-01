package com.example.compose.data.dto

data class Name (
    var official: String,
    var common: String,
    var nativeName: Map<String, CountryCode>
)

data class CountryCode (
     var official: String,
     var common: String
    )
