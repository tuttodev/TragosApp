package com.example.Tragosapp.di

import com.example.Tragosapp.data.DataSourceImp
import com.example.Tragosapp.domain.DataSource
import com.example.Tragosapp.domain.Repo
import com.example.Tragosapp.domain.RepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun bindRepoImpl(repoImp: RepoImp): Repo

    @Binds
    abstract fun bindDataSourceImpl(dataSourceImp: DataSourceImp): DataSource
}