package com.example.withfilms.domain.usecases

import com.example.withfilms.domain.repository.Repository
import javax.inject.Inject

class GetFilmStaffByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(filmId: Int) =
        repository.getFilmStaffById(filmId)
}