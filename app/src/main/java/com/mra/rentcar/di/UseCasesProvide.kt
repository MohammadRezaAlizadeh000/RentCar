package com.mra.rentcar.di

import com.mra.rentcar.domin.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesProvide {

    @Binds
    abstract fun provideUserLoginUseCase(impl: UserLoginUseCaseImpl): UserLoginUseCase

    @Binds
    abstract fun provideGetCarsListUseCase(impl: GetCarsUseCaseImpl): GetCarsUseCase

    @Binds
    abstract fun provideBookingCarUseCase(impl: BookingCarUseCaseImpl): BookingCarUseCase


}