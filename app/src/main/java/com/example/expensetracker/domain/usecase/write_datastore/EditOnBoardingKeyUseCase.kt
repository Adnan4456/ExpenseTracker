package com.example.expensetracker.domain.usecase.write_datastore

import com.example.expensetracker.domain.repository.DataStoreRepository
import javax.inject.Inject

class EditOnBoardingKeyUseCase
@Inject constructor(
    private val dataStoreRepository: DataStoreRepository
){
    suspend operator fun invoke(completed: Boolean){
        dataStoreRepository.writeOnBoardingKeyToDataStore(completed)
    }
}