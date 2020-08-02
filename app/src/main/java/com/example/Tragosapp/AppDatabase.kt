package com.example.Tragosapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.domain.DrinkDao

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
}