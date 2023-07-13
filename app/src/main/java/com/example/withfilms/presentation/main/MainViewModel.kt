package com.example.withfilms.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.withfilms.domain.model.Film
import com.example.withfilms.domain.usecases.GetTopFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTopFilmsUseCase: GetTopFilmsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(emptyFlow<PagingData<Film>>())
    val uiState = _uiState.asStateFlow()

    init {
        getTopFilms()
    }


    private fun getTopFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            val film = getTopFilmsUseCase.invoke()
            _uiState.value = film
        }
    }
}