package com.example.expensetracker.domain.usecase.read_database


import com.example.expensetracker.data.local.entity.TranscationDto
import com.example.expensetracker.domain.repository.TranscationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTranscationUseCase
@Inject constructor(
    private val transcationRepository: TranscationRepository
) {
    operator fun invoke(): Flow<List<TranscationDto>> {
        return transcationRepository.getAllTranscations()
    }
}