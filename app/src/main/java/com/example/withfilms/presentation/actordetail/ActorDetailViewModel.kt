package com.example.withfilms.presentation.actordetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.withfilms.data.Repository
import com.example.withfilms.domain.model.ActorDetail
import com.example.withfilms.domain.model.ActorFilms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var actorId: Int? = null

    private val _actorDetailUiState = MutableStateFlow(ActorDetailUiState())
    val actorDetailUiState = _actorDetailUiState.asStateFlow()

    fun getActorDetail(actorId: Int) {
        if(actorId != this.actorId) {
            viewModelScope.launch {
                val actorDetail = repository.getActorDetailById(actorId)
                emitSuccess(actorDetail)
            }
        }
        this.actorId = actorId
    }

    private fun emitSuccess(actorDetail: ActorDetail) {
        _actorDetailUiState.update { value ->
            value.copy(
                nameEn = actorDetail.nameEn ?: "",
                nameRu = actorDetail.nameRu ?: "",
                age = actorDetail.age.toString(),
                birthday = actorDetail.birthday,
                death = actorDetail.death,
                sex = if(actorDetail.sex == "MALE") "мужской" else "женский",
                films = actorDetail.films,
                poster = actorDetail.posterUrl,
                profession = actorDetail.profession,

                isLoading = false
            )
        }
    }
}

data class ActorDetailUiState(
    val nameEn: String = "",
    val nameRu: String = "",
    val age: String = "",
    val birthday: String = "",
    val death: String = "",
    val sex: String = "",
    val films: List<ActorFilms> = emptyList(),
    val poster: String = "",
    val profession: String = "",

    val isLoading: Boolean = true
)