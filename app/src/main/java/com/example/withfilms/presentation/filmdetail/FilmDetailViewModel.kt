package com.example.withfilms.presentation.filmdetail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.domain.usecases.GetFilmDetailByIdUseCase
import com.example.withfilms.domain.usecases.GetFilmStaffByIdUseCase
import com.example.withfilms.util.LoadState
import com.example.withfilms.util.Request.Error
import com.example.withfilms.util.Request.Loading
import com.example.withfilms.util.Request.Success
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
    val filmStaffList: List<FilmStaff> = emptyList(),
    val filmStaffMap: Map<String, List<FilmStaff>> = emptyMap(),
    val loadState: LoadState = LoadState.LOADING
)

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmDetailByIdUseCase: GetFilmDetailByIdUseCase,
    private val getFilmStaffByIdUseCase: GetFilmStaffByIdUseCase
) : ViewModel() {

    private var filmId: Int? = null

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState = _detailUiState.asStateFlow()

    fun onStart(filmId: Int) {
        if (this.filmId != filmId) {
            viewModelScope.launch(Dispatchers.IO) {
                getFilmStaff(filmId = filmId)
                getFilmDetail(filmId = filmId)
            }
        }
        this.filmId = filmId
    }

    private suspend fun getFilmDetail(filmId: Int) {
        getFilmDetailByIdUseCase.invoke(filmId).collect { request ->
            when (request) {
                is Error -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.ERROR) }
                }

                is Loading -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.LOADING) }
                }

                is Success -> {
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
                is Error -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.ERROR) }
                }

                is Loading -> {
                    _detailUiState.update { value -> value.copy(loadState = LoadState.LOADING) }
                }

                is Success -> {
                    _detailUiState.update { value ->
                        value.copy(
                            loadState =
                            if (value.loadState == LoadState.ERROR) LoadState.ERROR
                            else LoadState.SUCCESS,
                            filmStaffList = request.data.filter { it.nameRu.isNotBlank() },
                            filmStaffMap = request.data.groupBy { it.professionText }
                        )
                    }
                }
            }

        }
    }
}