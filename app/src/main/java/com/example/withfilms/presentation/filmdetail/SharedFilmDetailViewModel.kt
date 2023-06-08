package com.example.withfilms.presentation.filmdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.data.Repository
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.Staff
import com.example.withfilms.domain.model.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedFilmDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var filmId: Int? = null

    private val _filmDetailUiState = MutableStateFlow(
        FilmDetailUiState(isLoading = true)
    )
    val filmUiState = _filmDetailUiState.asStateFlow()

    fun onStart(
        filmId: Int
    ) {
        if (filmId != this.filmId) {
            viewModelScope.launch {
                val film = repository.getFilmDetailById(filmId)
                val filmStaff = repository.getFilmStaffByFilmId(filmId)

                successFilmDetail(film, filmStaff)
            }
            this.filmId = filmId
        }
    }

   private fun successFilmDetail(movie: FilmDetail, staff: List<Staff>) {

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
                isLoading = false,

                staff = staff
            )
        }
    }
}

data class FilmDetailUiState(
    val filmId: Int = 0,
    val filmName: String = "",
    val rating: String = "",
    val genres: List<Genre> = emptyList(),
    val description: String = "",
    val filmLength: Int = 0,
    val year: String = "",
    val posterPreview: String = "",
    val poster: String = "",
    val staff: List<Staff> = emptyList(),

    val isLoading: Boolean = false,
)

