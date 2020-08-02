package com.example.Tragosapp.domain

import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.vo.Resource

interface Repo {
    suspend fun getTragosList(drinkName: String): Resource<List<Drink>>
    suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>>
    suspend fun saveDrink(drink: DrinkEntity)
    suspend fun deleteDrink(drink: DrinkEntity)
}