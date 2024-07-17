package com.duongvh19.plantsapp.fragment.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.duongvh19.plantsapp.data.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(){

    val initPlant = savedStateHandle.get<Plant>("plant_item")

    private val _plant = MutableStateFlow(initPlant)
    val plant = _plant.asStateFlow()

    fun setPlant(plant: Plant) {
        savedStateHandle["plant_item"] = plant
    }

}