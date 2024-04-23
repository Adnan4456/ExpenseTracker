package com.example.expensetracker.domain.usecase.read_database

import com.example.expensetracker.data.local.model.entity.TranscationDto
import com.example.expensetracker.domain.repository.TranscationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Get7DayTranscation
@Inject constructor(
    private val transcationRepository: TranscationRepository
){
    operator fun invoke(transcationType: String): Flow<List<TranscationDto>> {
        return transcationRepository.get7DayTranscation(transcationType)
    }
}