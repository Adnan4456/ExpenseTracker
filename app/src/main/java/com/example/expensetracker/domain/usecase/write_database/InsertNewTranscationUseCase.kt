package com.example.expensetracker.domain.usecase.write_database

import com.example.expensetracker.data.local.entity.TranscationDto
import com.example.expensetracker.domain.repository.TranscationRepository
import javax.inject.Inject

class InsertNewTranscationUseCase
@Inject constructor(
    private val transcationRepository: TranscationRepository
){
    suspend operator fun invoke(dailyExpense : TranscationDto){
        transcationRepository.insertTranscation(dailyExpense)
    }
}