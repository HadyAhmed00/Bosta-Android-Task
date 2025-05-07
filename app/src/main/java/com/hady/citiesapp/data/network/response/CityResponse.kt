package com.hady.citiesapp.data.network.response

import com.hady.citiesapp.data.network.ToModel
import com.hady.citiesapp.domain.model.City

typealias CitiesResponse = List<CityResponse>

class CityResponse(
     val cityCode: String?,
     val cityId: String?,
     val cityName: String?,
     val cityOtherName: String?,
     val districts: DistrictsResponse?,
     val dropOffAvailability: Boolean?,
     val pickupAvailability: Boolean?
): ToModel<City> {
     override fun toModel(): City {
          return City(
                id = cityId,
                name = cityName,
                nameAr = cityOtherName,
                districts = districts?.map { it.toModel() }
          )
     }
}