package com.example.dinosaurs.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaurs.ui.network.DinosaursApi
import com.example.dinosaurs.ui.network.DinosaursPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinosaursUiState {
    data class Success(val photos: DinosaursPhoto) : DinosaursUiState
    object Error : DinosaursUiState
    object Loading : DinosaursUiState
}

class DinosaursViewModel : ViewModel() {
    var dinosaursUiState: DinosaursUiState by mutableStateOf(DinosaursUiState.Loading)
        private set

    init {
        getDinosaursPhotos()
    }

    fun getDinosaursPhotos() {
            viewModelScope.launch {
                dinosaursUiState = try {
                    DinosaursUiState.Success(DinosaursApi.retrofitService.getPhotos()[0])
                }  catch (e: IOException) {
                    DinosaursUiState.Error
                }
        }
    }
}