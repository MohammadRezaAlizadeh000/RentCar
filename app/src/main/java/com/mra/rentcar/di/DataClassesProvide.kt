package com.mra.rentcar.di

import com.mra.rentcar.data.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataClassesProvide {

    @Binds
    abstract fun provideRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun provideRepository(impl: RepositoryImpl): Repository

    @Binds
    abstract fun provideMockDataSource(impl: MockDataSourceImpl): MockDataSource
}