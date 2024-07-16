package com.duongvh19.plantsapp.data.repositories

import com.duongvh19.plantsapp.api.ApiService
import com.duongvh19.plantsapp.data.dao.PlantDao
import com.duongvh19.plantsapp.data.model.Plant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(
    private val plantDao: PlantDao,
    private val apiService: ApiService
){

    fun loadItems() : Flow<List<Plant>> = plantDao.loadAll()

    suspend fun insertAll(plants : List<Plant>) = plantDao.insertAll(plants)
}