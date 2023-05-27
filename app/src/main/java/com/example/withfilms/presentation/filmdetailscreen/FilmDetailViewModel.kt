package com.example.withfilms.presentation.filmdetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.data.Repository
import com.example.withfilms.data.toFilmDetail
import com.example.withfilms.data.toFilmStaff
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.domain.model.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _filmDetailUiState = MutableStateFlow(
        FilmDetailUiState(
            isLoading = true
        )
    )
    val filmUiState: StateFlow<FilmDetailUiState> = _filmDetailUiState.asStateFlow()

    private val _filmStaffUiState = MutableStateFlow(FilmStaffUiState())
    val filmStaffUiState: StateFlow<FilmStaffUiState> = _filmStaffUiState.asStateFlow()

    fun onStart(
        filmId: Long
    ) {
        viewModelScope.launch {
            val film = repository.getFilmDetailById(filmId).toFilmDetail()

            val filmStaff = repository.getFilmStaffById(filmId).map {
                it.toFilmStaff()
            }

            successFilmDetail(film)
            successFilmStaff(filmStaff)
        }
    }

    private fun successFilmDetail(movie: FilmDetail) {

        _filmDetailUiState.update { state ->
            state.copy(
                filmId = movie.filmId,
                filmName = movie.filmName,
                rating = movie.rating,
                genres = movie.genres,
                description = movie.description,
                filmLength = movie.filmLength,
                year = movie.year,
                posterPreview = movie.posterPreview,
                poster = movie.poster,
                isLoading = false
            )
        }

    }

    private fun successFilmStaff(staff: List<FilmStaff>) {
        _filmStaffUiState.update {state ->
                state.copy(
                    staff = staff
                )
        }
    }

}


data class FilmDetailUiState(
    val filmId: Long = 0,
    val filmName: String = "",
    val rating: String = "",
    val genres: List<Genre> = emptyList(),
    val description: String = "",
    val filmLength: Int = 0,
    val year: String = "",
    val posterPreview: String = "",
    val poster: String = "",

    val isLoading: Boolean = false,
)

data class FilmStaffUiState(
    val staff: List<FilmStaff> = emptyList()
)
