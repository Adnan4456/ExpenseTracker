package com.example.expensetracker.domain.usecase.write_datastore

import com.example.expensetracker.domain.repository.DataStoreRepository
import javax.inject.Inject

class EditCurrencyUseCase
@Inject constructor(
    private val dataStoreRepository: DataStoreRepository
)
{

    suspend operator fun invoke(currency: String){
        dataStoreRepository.writeCurrencyToDataStore(currency)
    }
}