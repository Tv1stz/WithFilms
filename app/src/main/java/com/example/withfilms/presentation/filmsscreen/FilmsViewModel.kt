package com.example.withfilms.presentation.filmsscreen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.data.Repository
import com.example.withfilms.data.remote.model.films.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _filmsUiState = MutableStateFlow(FilmsUiState())
    val filmUiState: StateFlow<FilmsUiState> = _filmsUiState.asStateFlow()

    var search by mutableStateOf("")
        private set

    fun searchFilms(request: String) {
        search = request
    }
    private fun addFilms() {
        viewModelScope.launch {
            val films = repository.getTopFilms()

            _filmsUiState.update { state ->
                state.copy(
                    filmList = films.films
                )
            }
        }
    }

    init {
        addFilms()
    }



}

data class FilmsUiState(
    val filmList: List<Film> = emptyList()
)
