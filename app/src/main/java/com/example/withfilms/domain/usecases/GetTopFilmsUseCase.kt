package com.example.withfilms.domain.usecases

import com.example.withfilms.domain.repository.Repository
import javax.inject.Inject

class GetTopFilmsUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke() =
        repository.getTopFilms()
}