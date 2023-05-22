package com.example.withfilms.presentation.screen

import androidx.paging.PagingData
import com.example.withfilms.data.remote.model.Film
import kotlinx.coroutines.flow.Flow

sealed interface FilmUiState{
    data class Success(val data: Flow<PagingData<Film>>): FilmUiState
    object Loading: FilmUiState
}



