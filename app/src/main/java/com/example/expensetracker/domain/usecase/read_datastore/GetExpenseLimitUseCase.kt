package com.example.expensetracker.domain.usecase.read_datastore

import com.example.expensetracker.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpenseLimitUseCase
@Inject constructor(
    private val dataStoreRepository: DataStoreRepository
){
    suspend operator fun invoke() : Flow<Double> {
        return dataStoreRepository.readExpenseLimitFromDataStore()
    }
}