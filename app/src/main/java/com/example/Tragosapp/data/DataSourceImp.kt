package com.example.Tragosapp.data

import com.example.Tragosapp.AppDatabase
import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.domain.DataSource
import com.example.Tragosapp.vo.Resource
import com.example.Tragosapp.vo.RetrofitClient

class DataSourceImp(private val appDatabase: AppDatabase): DataSource {
    override suspend fun getDrinksByName(drinkName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getDrinksByName(drinkName).drinkList)
    }

    override suspend fun saveDrinkIntoRoom(drink: DrinkEntity){
        appDatabase.drinkDao().insertFavoriteDrink(drink)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.drinkDao().getAllFavoriteDrinks())
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        appDatabase.drinkDao().deleteDrink(drink)
    }
}