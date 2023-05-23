package com.example.withfilms.presentation.homescreen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.withfilms.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    var search by mutableStateOf("")
        private set

    fun searchFilms(request: String) {
        search = request
    }

    fun getFilm() = repository.getTopFilm()

}

