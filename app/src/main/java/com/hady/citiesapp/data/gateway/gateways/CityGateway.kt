package com.hady.citiesapp.data.gateway.gateways

import com.hady.citiesapp.domain.model.Cities

interface CityGateway {
    suspend fun fetchAllCities(): Cities
}