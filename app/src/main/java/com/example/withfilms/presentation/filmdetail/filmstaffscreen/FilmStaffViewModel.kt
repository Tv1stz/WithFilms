package com.example.withfilms.presentation.filmdetail.filmstaffscreen

import androidx.lifecycle.ViewModel
import com.example.withfilms.R
import com.example.withfilms.domain.model.FilmStaff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilmStaffViewModel @Inject constructor(): ViewModel() {

    private val _staffUiState = MutableStateFlow(
        StaffUiState(isLoading = true)
    )
    val staffUiState = _staffUiState.asStateFlow()

    private var localStaff: MutableMap<String, MutableList<FilmStaff>> = mutableMapOf(
        "${R.string.director}" to mutableListOf(),
        "${R.string.actor}" to mutableListOf(),
        "${R.string.producer}" to mutableListOf(),
        "${R.string.writer}" to mutableListOf(),
        "${R.string.composer}" to mutableListOf(),
        "${R.string.design}" to mutableListOf(),
        "${R.string.editor}" to mutableListOf(),
        "${R.string.operator}" to mutableListOf()
    )

    fun sortedStaff(staff: List<FilmStaff>) {
        for (person in staff) {
            if (person.nameRu.isNotBlank()) {
                when (person.professionKey) {
                    "DIRECTOR" -> { localStaff["${R.string.director}"]?.add(person) }
                    "ACTOR" -> { localStaff["${R.string.actor}"]?.add(person) }
                    "PRODUCER" -> { localStaff["${R.string.producer}"]?.add(person) }
                    "WRITER" -> { localStaff["${R.string.writer}"]?.add(person) }
                    "COMPOSER" -> { localStaff["${R.string.composer}"]?.add(person) }
                    "DESIGN" -> { localStaff["${R.string.design}"]?.add(person) }
                    "EDITOR" -> { localStaff["${R.string.editor}"]?.add(person) }
                    "OPERATOR" -> { localStaff["${R.string.operator}"]?.add(person) }
                }
            }
        }
        successStaff(localStaff)
    }

    private fun successStaff(staff: Map<String, List<FilmStaff>>) {
        _staffUiState.update { state ->
            state.copy(
                staff = staff,
                isLoading = false
            )
        }

    }
}

data class StaffUiState(
    val staff: Map<String, List<FilmStaff>> = emptyMap(),
    val isLoading: Boolean = true,
)