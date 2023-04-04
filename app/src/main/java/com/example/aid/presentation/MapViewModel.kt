package com.example.aid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aid.data.data_source.local.entity.SmokePlaceEntity
import com.example.aid.data.repository.SmokePlaceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(private val repository: SmokePlaceRepository) : ViewModel() {
    var smokePlaces: List<SmokePlaceEntity> = listOf()

    fun getSmokePlaces() =
        CoroutineScope(Dispatchers.IO).launch {
            smokePlaces = repository.getAllSmokePlaceEntity()
        }

}

class MapViewModelFactory(private val repository: SmokePlaceRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MapViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}