package com.example.Tragosapp.data

import com.example.Tragosapp.AppDatabase
import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.domain.DataSource
import com.example.Tragosapp.domain.DrinkDao
import com.example.Tragosapp.vo.Resource
import com.example.Tragosapp.vo.RetrofitClient
import javax.inject.Inject

class DataSourceImp @Inject constructor (private val drinkDao: DrinkDao): DataSource {
    override suspend fun getDrinksByName(drinkName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getDrinksByName(drinkName).drinkList)
    }

    override suspend fun saveDrinkIntoRoom(drink: DrinkEntity){
        drinkDao.insertFavoriteDrink(drink)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return Resource.Success(drinkDao.getAllFavoriteDrinks())
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        drinkDao.deleteDrink(drink)
    }
}