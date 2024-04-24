package com.example.expensetracker.domain.usecase.write_datastore

import com.example.expensetracker.domain.repository.DataStoreRepository
import javax.inject.Inject

class EditExpenseLimitUseCase
    @Inject constructor(
        private val dataStoreRepository: DataStoreRepository
    ){

        suspend operator fun invoke(amount: Double){
            dataStoreRepository.writeExpenseLimitToDataStore(amount)
        }
}