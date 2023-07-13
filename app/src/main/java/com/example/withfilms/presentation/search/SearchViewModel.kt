package com.example.withfilms.presentation.search

import androidx.lifecycle.ViewModel
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.usecases.GetSearchFilmsByKeyWordUseCase
import com.example.withfilms.util.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchFilmsByKeyWordUseCase: GetSearchFilmsByKeyWordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
}

data class SearchUiState(
    val query: String = "",
    val films: List<Film> = emptyList(),
    val loadState: LoadState = LoadState.LOADING
)