package com.hady.citiesapp.data.gateway.repositories

import com.hady.citiesapp.data.gateway.gateways.CityGateway
import com.hady.citiesapp.data.network.api.CityApiService
import com.hady.citiesapp.domain.model.Cities
import timber.log.Timber
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val cityApiService: CityApiService
): CityGateway {
    override suspend fun fetchAllCities(): Cities {
        val response = cityApiService.fetchAllCities()
        Timber.d("Cities Response: ${response.body()}")
        return response.body()
            ?.data?.map { it.toModel() } ?: emptyList()
    }
}