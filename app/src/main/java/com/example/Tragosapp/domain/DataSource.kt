package com.example.Tragosapp.domain

import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.vo.Resource

interface DataSource {
    suspend fun getDrinksByName(drinkName: String): Resource<List<Drink>>

    suspend fun saveDrinkIntoRoom(drink: DrinkEntity)

    suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>>

    suspend fun deleteDrink(drink: DrinkEntity)
}