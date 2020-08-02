package com.example.Tragosapp.domain

import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.vo.Resource
import javax.inject.Inject

class RepoImp @Inject constructor (private val dataSource: DataSource): Repo {
    override suspend fun getTragosList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinksByName(drinkName)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return dataSource.getFavoriteDrinks()
    }

    override suspend fun saveDrink(drink: DrinkEntity) {
        dataSource.saveDrinkIntoRoom(drink)
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        dataSource.deleteDrink(drink)
    }
}