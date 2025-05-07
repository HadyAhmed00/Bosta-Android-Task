package com.hady.citiesapp.data.gateway

import com.hady.citiesapp.data.gateway.gateways.CityGateway
import com.hady.citiesapp.data.gateway.repositories.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {
    @Binds
    abstract fun bindCityGateway(cityRepository: CityRepository): CityGateway
}