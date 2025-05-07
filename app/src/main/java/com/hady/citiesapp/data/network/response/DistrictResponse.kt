package com.hady.citiesapp.data.network.response

import com.hady.citiesapp.data.network.ToModel
import com.hady.citiesapp.domain.model.District

typealias DistrictsResponse = List<DistrictResponse>

class DistrictResponse(
    val coverage: String?,
    val districtId: String?,
    val districtName: String?,
    val districtOtherName: String?,
    val dropOffAvailability: Boolean?,
    val pickupAvailability: Boolean?,
    val zoneId: String?,
    val zoneName: String?,
    val zoneOtherName: String?
): ToModel<District> {
    override fun toModel(): District {
        return District(
            id = districtId,
            name = districtName,
            nameAr = districtOtherName,
            isUncovered = pickupAvailability == false
        )
    }
}