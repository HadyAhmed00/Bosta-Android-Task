package com.hady.citiesapp.domain.usecase

import com.hady.citiesapp.domain.model.Cities
import javax.inject.Inject

class SearchForCitiesUseCase @Inject constructor() {
    operator fun invoke(
        searchText: String? = null,
        allCities: Cities? = null,
    ): Cities{
        val cities = if (searchText.isNullOrEmpty()) {
            allCities ?: emptyList()
        } else {
            allCities?.filter { city ->
                city.name?.contains(searchText, ignoreCase = true) == true
                        || city.nameAr?.contains(searchText, ignoreCase = true) == true
                        || city.districts?.any { district ->
                            district.name?.contains(searchText, ignoreCase = true) == true
                                    || district.nameAr?.contains(searchText, ignoreCase = true) == true
                        } == true
            }
        } ?: emptyList()
        return cities
    }
}