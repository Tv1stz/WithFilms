package com.example.withfilms.presentation.filmdetail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.domain.usecases.GetFilmDetailByIdUseCase
import com.example.withfilms.domain.usecases.GetFilmStaffByIdUseCase
import com.example.withfilms.util.LoadState
import com.example.withfilms.util.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
data class DetailUiState(
    val filmDetail: FilmDetail? = null,
    val filmStaff: List<FilmStaff> = emptyList(),
    val loadState: LoadState = LoadState.LOADING
)

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmDetailByIdUseCase: GetFilmDetailByIdUseCase,
    private val getFilmStaffByIdUseCase: GetFilmStaffByIdUseCase
) : ViewModel() {

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState = _detailUiState.asStateFlow()

    fun onStart(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getFilmStaff(filmId = filmId)
            getFilmDetail(filmId = filmId)
        }
    }

    private suspend fun getFilmDetail(filmId: Int) {
        getFilmDetailByIdUseCase.invoke(filmId).collect { request ->
            when (request) {
                is Request.Error -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.ERROR) }
                }

                is Request.Loading -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.LOADING) }
                }

                is Request.Success -> {
                    _detailUiState.update { value ->
                        value.copy(
                            loadState =
                            if (value.loadState == LoadState.ERROR) LoadState.ERROR
                            else LoadState.SUCCESS,
                            filmDetail = request.data
                        )
                    }
                }
            }
        }
    }

    private suspend fun getFilmStaff(filmId: Int) {
        getFilmStaffByIdUseCase.invoke(filmId).collect { request ->
            when (request) {
                is Request.Error -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.ERROR) }
                }

                is Request.Loading -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.LOADING) }
                }

                is Request.Success -> {
                    _detailUiState.update { value ->
                        value.copy(
                            loadState =
                            if (value.loadState == LoadState.ERROR) LoadState.ERROR
                            else LoadState.SUCCESS,
                            filmStaff = request.data
                        )
                    }
                }
            }
        }
    }

}