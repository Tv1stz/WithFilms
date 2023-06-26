package com.example.withfilms.domain.usecases

import com.example.withfilms.domain.repository.Repository
import javax.inject.Inject

class GetPersonDetailByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(personId: Int) =
        repository.getPersonDetailById(personId)
}