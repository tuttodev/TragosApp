package com.example.Tragosapp.di

import android.content.Context
import androidx.room.Room
import com.example.Tragosapp.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "drink_table").build()

    @Singleton
    @Provides
    fun provideDrinkDao(db: AppDatabase) = db.drinkDao()
}