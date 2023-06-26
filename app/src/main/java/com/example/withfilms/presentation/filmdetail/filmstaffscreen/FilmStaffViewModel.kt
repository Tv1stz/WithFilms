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
        "Режиссер" to mutableListOf(),
        "Актер" to mutableListOf(),
        "Продюсер" to mutableListOf(),
        "Сцинарист" to mutableListOf(),
        "Композитор" to mutableListOf(),
        "Художник" to mutableListOf(),
        "Монтажер" to mutableListOf(),
        "Оператор" to mutableListOf()
    )

    fun sortedStaff(staff: List<FilmStaff>) {
        for (person in staff) {
            if (person.nameRu.isNotBlank()) {
                when (person.professionKey) {
                    "DIRECTOR" -> { localStaff["Режиссер"]?.add(person) }
                    "ACTOR" -> { localStaff["Актер"]?.add(person) }
                    "PRODUCER" -> { localStaff["Продюсер"]?.add(person) }
                    "WRITER" -> { localStaff["Сцинарист"]?.add(person) }
                    "COMPOSER" -> { localStaff["Композитор"]?.add(person) }
                    "DESIGN" -> { localStaff["Художник"]?.add(person) }
                    "EDITOR" -> { localStaff["Монтажер"]?.add(person) }
                    "OPERATOR" -> { localStaff["Оператор"]?.add(person) }
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