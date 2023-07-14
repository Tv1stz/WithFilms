package com.example.withfilms.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.withfilms.data.db.FilmEntity
import com.example.withfilms.data.toFilm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    pager: Pager<Int, FilmEntity>
) : ViewModel() {

        val filmFlow = pager
            .flow
            .map { pagingData ->
                pagingData.map { it.toFilm() }
            }
            .cachedIn(viewModelScope)


}