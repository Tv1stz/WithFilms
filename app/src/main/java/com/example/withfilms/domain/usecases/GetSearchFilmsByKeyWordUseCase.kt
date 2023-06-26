package com.example.withfilms.domain.usecases

import com.example.withfilms.domain.repository.Repository
import javax.inject.Inject

class GetSearchFilmsByKeyWordUseCase@Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(searchQuery: String) =
        repository.getSearchFilmByKeyWord(searchQuery)
}