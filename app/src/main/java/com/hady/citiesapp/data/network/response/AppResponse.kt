package com.hady.citiesapp.data.network.response

class AppResponse<T>(
    val data: T?,
    val message: String?,
    val success: Boolean?
)