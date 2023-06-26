package com.example.withfilms.presentation.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.usecases.GetSearchFilmsByKeyWordUseCase
import com.example.withfilms.presentation.utils.LoadState
import com.example.withfilms.util.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchFilmsByKeyWordUseCase: GetSearchFilmsByKeyWordUseCase
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            queryFlow
                .sample(500L)
                .mapLatest(::handleQuery)
                .collect()
        }
    }

    fun onNewQuery(query: String) {
        _uiState.update { value ->
            value.copy(
                query = query
            )
        }
        queryFlow.value = query
    }

    private fun handleQuery(query: String) {
        emitShowOrHideProgress(query)

        if (query.isNotEmpty()) {
            handleSearchFilm(query)
        }
    }

    private fun emitShowOrHideProgress(query: String) {
        _uiState.update { value ->
            if (query.isEmpty()) {
                value.copy(
                    loadState = LoadState.ERROR,
                    films = emptyList()
                )
            } else {
                value.copy(
                    loadState = LoadState.LOADING,
                )
            }
        }
    }

    private fun handleSearchFilm(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1_000L)

            getSearchFilmsByKeyWordUseCase.invoke(query).collect {request ->
                when (request) {
                    is Request.Loading -> {
                        _uiState.update { value ->
                            value.copy(
                                loadState = LoadState.LOADING
                            )
                        }
                    }
                    is Request.Error -> {
                        emitErrorState()
                    }
                    is Request.Success -> {
                        val data = request.data
                        emitSuccessState(films = data)
                    }
                }
            }
        }



    }

    private fun emitErrorState() {
        _uiState.update { value ->
            value.copy(
                loadState = LoadState.ERROR,
                films = emptyList()
            )
        }
    }

    private fun emitSuccessState(films: List<Film>) {
        _uiState.update { value ->
            value.copy(
                films = films,
                loadState = LoadState.SUCCESS
            )
        }
    }
}

data class SearchUiState(
    val query: String = "",
    val films: List<Film> = emptyList(),
    val loadState: LoadState = LoadState.LOADING
)