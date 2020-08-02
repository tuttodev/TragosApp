package com.example.Tragosapp.domain

import androidx.room.*
import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity

@Dao
interface DrinkDao {
    @Query("SELECT * FROM DrinkEntity")
    suspend fun getAllFavoriteDrinks(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDrink(drink: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)
}