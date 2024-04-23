package com.example.expensetracker.domain.usecase.read_database

import com.example.expensetracker.data.local.model.entity.TranscationDto
import com.example.expensetracker.domain.repository.TranscationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTranscationByAccountUseCase @Inject constructor(
    private val transcationRepository: TranscationRepository
){

    operator fun invoke(accountType: String): Flow<List<TranscationDto>> {
        return transcationRepository.getTranscationByAccountType(accountType)
    }
}