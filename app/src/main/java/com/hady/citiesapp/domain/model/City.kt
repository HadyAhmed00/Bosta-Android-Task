package com.hady.citiesapp.domain.model

typealias Cities = List<City>

data class City(
    val id: String?,
    val name: String?,
    val nameAr: String?,
    val districts: Districts?,
)
