package com.hady.citiesapp.domain.model

typealias Districts = List<District>

data class District(
    val id: String?,
    val name: String?,
    val nameAr: String?,
    val isUncovered: Boolean?,
)
