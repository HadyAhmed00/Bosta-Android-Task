package com.hady.citiesapp.data.network

interface ToModel<T> {
    fun toModel(): T
}