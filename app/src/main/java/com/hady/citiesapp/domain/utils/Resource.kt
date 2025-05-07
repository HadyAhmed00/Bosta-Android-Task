package com.hady.citiesapp.domain.utils

sealed class Resource<T> {
    data class Idle<T>(val data: T? = null) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val cause: Throwable? = null) : Resource<T>()
    class Loading<T> : Resource<T>()
}