package com.duongvh19.plantsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duongvh19.plantsapp.api.ApiService
import com.duongvh19.plantsapp.data.dao.PlantDao
import com.duongvh19.plantsapp.data.model.Plant
import com.duongvh19.plantsapp.data.repositories.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantDao: PlantDao,
    private val apiService: ApiService,
    private val plantRepository: PlantRepository
) : ViewModel(){

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants : StateFlow<List<Plant>> get() = _plants

    init {
        fetchPlant()
    }
    private fun fetchPlant() {
        viewModelScope.launch {
            val plants = apiService.getAllPlants()
            plantRepository.insertAll(plants)

            val plantsLocal = plantRepository.loadItems()
            plantsLocal.collect {
                _plants.value = it
            }
        }
    }
}