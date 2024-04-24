package com.example.expensetracker.domain.usecase.write_datastore

import com.example.expensetracker.domain.repository.DataStoreRepository
import javax.inject.Inject

class EditLimitDurationUseCase
@Inject constructor(
    private val dataStoreRepository: DataStoreRepository
){
    suspend  operator fun invoke(duration : Int){
        dataStoreRepository.writeLimitDurationToDataStore(duration)
    }
}