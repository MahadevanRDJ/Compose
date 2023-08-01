package com.example.compose.network

import com.example.compose.data.dto.Country
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val URL = "https://restcountries.com/v3.1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(URL)
    .build()

interface RestCountryAPIService {
    @GET("independent?status=true&fields=name,capital,currencies,population,area,flags")
    fun getCountryList(): Deferred<MutableList<Country>>
}

object RestCountryAPI {
    val retrofitService: RestCountryAPIService by lazy {
        retrofit.create(RestCountryAPIService::class.java)
    }
}
