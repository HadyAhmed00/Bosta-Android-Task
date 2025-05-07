package com.hady.citiesapp.ui.selectdeliveryarea

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hady.citiesapp.data.gateway.gateways.CityGateway
import com.hady.citiesapp.domain.model.Cities
import com.hady.citiesapp.domain.usecase.SearchForCitiesUseCase
import com.hady.citiesapp.domain.utils.Resource
import com.hady.citiesapp.ui.selectdeliveryarea.model.AreaItemSelection
import com.hady.citiesapp.ui.selectdeliveryarea.model.CityItemSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DeliveryAreaViewModel @Inject constructor(
    application: Application,
    private val cityGateway: CityGateway,
    private val searchForCitiesUseCase: SearchForCitiesUseCase,
) : AndroidViewModel(application) {
    val allCitiesStatus = MutableLiveData<Resource<Cities>>()
    val citiesLiveData = MutableLiveData<List<Any>> ()
    val searchTextLiveData = MutableLiveData<String?>()
    val selectedCityIdLiveData = MutableLiveData<String?>()

    init {
        fetchAllCities()
        searchTextLiveData.observeForever { searchForCities() }
    }

    private fun fetchAllCities() = viewModelScope.launch {
        allCitiesStatus.value = Resource.Loading()
        try {
            val cities = withContext(Dispatchers.IO) {
                cityGateway.fetchAllCities()
            }
            allCitiesStatus.value = Resource.Success(cities)
            searchForCities()
        } catch (e: Exception) {
            allCitiesStatus.value = Resource.Error(e)
        }
    }

    fun searchForCities(
        searchText: String? = searchTextLiveData.value,
        citiesResponse: Resource<Cities>? = allCitiesStatus.value
    ) {
        if (citiesResponse !is Resource.Success) return
        val cities = searchForCitiesUseCase(searchText, citiesResponse.data)
        citiesLiveData.value = mapSearchData(cities)
    }

    private fun mapSearchData(
        cities: Cities,
        selectedCityId: String? = selectedCityIdLiveData.value
    ): List<Any>{
        val list = mutableListOf<Any>()
        cities.map {
            list.add(CityItemSelection(
                name = it.name ?: "",
                id = it.id ?: "",
                isSelected = it.id == selectedCityId
            ))
            if (it.id == selectedCityId)
                list.addAll(
                    it.districts?.map {
                    AreaItemSelection(
                        id = it.id ?: "",
                        name = it.name ?: "",
                        isUnCovered = it.isUncovered == true
                    ) } ?: emptyList()
                )
        }
        return list
    }

    fun onCitySelected(
        city: CityItemSelection,
        selectedCityId: String = selectedCityIdLiveData.value ?: ""
    ) {
        if (city.id == selectedCityId)
            selectedCityIdLiveData.value = null
        else
            selectedCityIdLiveData.value = city.id
        searchForCities()
    }

    fun refresh() {
        fetchAllCities()
    }
}