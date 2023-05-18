package com.mra.rentcar.di

import com.mra.rentcar.data.network.BASE_URL
import com.mra.rentcar.data.network.CarsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitOkHttp(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitApiService(retrofit: Retrofit): CarsApiService {
        return retrofit.create(CarsApiService::class.java)
    }
}