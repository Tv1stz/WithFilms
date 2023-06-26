package com.example.withfilms.presentation.persondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.domain.model.PersonDetail
import com.example.withfilms.domain.usecases.GetPersonDetailByIdUseCase
import com.example.withfilms.presentation.utils.LoadState
import com.example.withfilms.util.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val getPersonDetailByIdUseCase: GetPersonDetailByIdUseCase
) : ViewModel() {

    @Volatile
    private var personId: Int? = null

    private val _uiState = MutableStateFlow(PersonDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun getPersonDetail(personId: Int) {
        if (personId != this.personId) {
            viewModelScope.launch(Dispatchers.IO) {
                getPersonDetailByIdUseCase.invoke(personId).collect { request ->
                    when (request) {
                        is Request.Error -> {
                            _uiState.update { value ->
                                value.copy(
                                    loadState = LoadState.ERROR
                                )
                            }
                        }

                        is Request.Loading -> {
                            _uiState.update { value ->
                                value.copy(
                                    loadState = LoadState.LOADING
                                )
                            }
                        }

                        is Request.Success -> {
                            val data = request.data
                            _uiState.update { value ->
                                value.copy(
                                    personDetail = data,
                                    loadState = LoadState.SUCCESS
                                )
                            }

                        }
                    }
                }
            }
        }
        this.personId = personId
    }
}

data class PersonDetailUiState(
    val personDetail: PersonDetail? = null,
    val loadState: LoadState = LoadState.SUCCESS
)