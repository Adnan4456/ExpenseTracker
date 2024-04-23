package com.example.expensetracker.domain.usecase.write_database

import com.example.expensetracker.data.local.entity.AccountDto
import com.example.expensetracker.domain.repository.TranscationRepository
import javax.inject.Inject

class InsertAccountsUseCase
    @Inject constructor(
        private val transcationRepository: TranscationRepository
    ){

        suspend operator fun invoke(accounts: List<AccountDto>){
            transcationRepository.insertAccount(accounts)
        }
}