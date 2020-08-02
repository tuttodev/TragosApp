package com.example.Tragosapp.domain

import com.example.Tragosapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getDrinksByName(@Query(value = "s") drinkName: String): DrinkList
}