package com.example.withfilms.presentation.filmsscreen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.withfilms.data.Repository
import com.example.withfilms.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _filmsUiState = MutableStateFlow(emptyFlow<PagingData<Film>>())
    val filmUiState: StateFlow<Flow<PagingData<Film>>> = _filmsUiState.asStateFlow()

    init {
        getTopFilms()
    }

    private fun getTopFilms() {
        val films = repository.getTopFilms().cachedIn(viewModelScope)
        viewModelScope.launch {
            _filmsUiState.value = films
        }

    }

    var search by mutableStateOf("")
        private set

    fun searchFilms(request: String) {
        search = request
    }


}
