package com.example.withfilms.presentation.filmdetail.filmstaffscreen

import androidx.lifecycle.ViewModel
import com.example.withfilms.domain.model.Staff
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

    private var localStaff: MutableMap<String, MutableList<Staff>> = mutableMapOf(
        "Режиссер" to mutableListOf(),
        "Актер" to mutableListOf(),
        "Продюсер" to mutableListOf(),
        "Сцинарист" to mutableListOf(),
        "Оператор" to mutableListOf(),
        "Композитор" to mutableListOf(),
        "Художник" to mutableListOf(),
        "Монтажер" to mutableListOf()
    )

    fun sortedStaff(staff: List<Staff>) {
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

    private fun successStaff(staff: Map<String, List<Staff>>) {
        _staffUiState.update { state ->
            state.copy(
                staff = staff,
                isLoading = false
            )
        }

    }
}

data class StaffUiState(
    val staff: Map<String, List<Staff>> = emptyMap(),
    val isLoading: Boolean = true,
)