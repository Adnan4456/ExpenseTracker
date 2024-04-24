package com.example.expensetracker.domain.usecase.write_database

import com.example.expensetracker.domain.repository.TranscationRepository
import javax.inject.Inject

class EraseTranscationUseCase
@Inject constructor(
val transcationRepository: TranscationRepository
) {
    operator fun invoke(){
        transcationRepository.eraseTranscation()
    }
}