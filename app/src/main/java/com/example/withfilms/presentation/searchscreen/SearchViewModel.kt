package com.example.withfilms.presentation.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.data.Repository
import com.example.withfilms.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val repository: Repository
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _searchResult = MutableStateFlow(SearchUiState())
    val searchResult = _searchResult.asStateFlow()

    init {
        viewModelScope.launch {
            queryFlow
                .sample(500L)
                .mapLatest(::handleQuery)
                .collect()
        }
    }

    fun onNewQuery(query: String) {
        _searchResult.update { value ->
            value.copy(
                query = query
            )
        }
        queryFlow.value = query
    }

    private suspend fun handleQuery(query: String) {
        emitShowOrHideProgress(query)

        if (query.isNotEmpty()) {
            handleSearchFilm(query)
        }
    }

    private fun emitShowOrHideProgress(query: String) {
        _searchResult.update { value ->
            if (query.isEmpty()) {
                value.copy(
                    isLoading = false,
                    films = emptyList()
                )
            } else {
                value.copy(
                    isLoading = true
                )
            }
        }
    }

    private suspend fun handleSearchFilm(query: String) {
        delay(1_000L)
        val filmResult = repository.getSearchFilmByKeyWord(query)
        emitSuccessState(filmResult)
    }

    private fun emitSuccessState(films: List<Film>) {
        _searchResult.update { value ->
            value.copy(
                isLoading = false,
                films = films
            )
        }
    }
}

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = true,
    val films: List<Film> = emptyList()
)