package com.hady.citiesapp.data.network.api


import com.hady.citiesapp.data.network.response.AppResponse
import com.hady.citiesapp.data.network.response.CitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CityApiService {
    @GET("cities/getAllDistricts")
    suspend fun fetchAllCities(): Response<AppResponse<CitiesResponse>>
}