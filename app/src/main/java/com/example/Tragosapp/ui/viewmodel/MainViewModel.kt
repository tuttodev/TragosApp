package com.example.Tragosapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.domain.Repo
import com.example.Tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel @ViewModelInject constructor (private val repo: Repo) : ViewModel() {

    private val drinksData = MutableLiveData<String>()

    fun setDrink(drinkName: String){
        drinksData.value = drinkName
    }

    init {
        setDrink("margarita")
    }

    val fetchDrinksList = drinksData.distinctUntilChanged().switchMap { drinkName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getTragosList(drinkName))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.saveDrink(drink)
        }
    }

    fun getFavoriteDrinks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(repo.getFavoriteDrinks())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.deleteDrink(drink)
        }
    }
}