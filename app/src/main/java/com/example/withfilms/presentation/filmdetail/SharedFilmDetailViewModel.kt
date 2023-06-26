package com.example.withfilms.presentation.filmdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.domain.usecases.GetFilmDetailByIdUseCase
import com.example.withfilms.domain.usecases.GetFilmStaffByIdUseCase
import com.example.withfilms.presentation.utils.LoadState
import com.example.withfilms.util.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedFilmDetailViewModel @Inject constructor(
    private val getFilmStaffByIdUseCase: GetFilmStaffByIdUseCase,
    private val getFilmDetailByIdUseCase: GetFilmDetailByIdUseCase
) : ViewModel() {

    private var filmId: Int? = null

    private val _uiState = MutableStateFlow(
        FilmDetailUiState()
    )
    val uiState = _uiState.asStateFlow()

    fun onStart(
        filmId: Int
    ) {
        if (filmId != this.filmId) {
            viewModelScope.launch {
                handleFilmDetail(filmId = filmId)
                handleFilmStaff(filmId = filmId)
            }
            this.filmId = filmId
        }
    }

    private suspend fun handleFilmDetail(filmId: Int) {
        getFilmDetailByIdUseCase.invoke(filmId).collect { request ->
            when (request) {
                is Request.Success -> {
                    _uiState.update { value ->
                        value.copy(
                            filmDetail = request.data,
                            loadState = LoadState.SUCCESS
                        )
                    }
                }
                is Request.Error -> _uiState.update { it.copy(loadState = LoadState.ERROR) }
                is Request.Loading -> _uiState.update { it.copy(loadState = LoadState.LOADING) }
            }
        }
    }

    private suspend fun handleFilmStaff(filmId: Int) {
        getFilmStaffByIdUseCase.invoke(filmId).collect { request ->
            when (request) {
                is Request.Success -> {
                    _uiState.update { value ->
                        value.copy(
                            filmStaff = request.data,
                            loadState = LoadState.SUCCESS
                        )
                    }
                }
                is Request.Error -> _uiState.update { it.copy(loadState = LoadState.ERROR) }
                is Request.Loading -> _uiState.update { it.copy(loadState = LoadState.LOADING) }
            }
        }
    }
}

data class FilmDetailUiState(
    val filmDetail: FilmDetail? = null,
    val filmStaff: List<FilmStaff> = emptyList(),
    val loadState: LoadState = LoadState.LOADING
)

