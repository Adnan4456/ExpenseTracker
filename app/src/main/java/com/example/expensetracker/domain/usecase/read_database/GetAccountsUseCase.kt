package com.example.expensetracker.domain.usecase.read_database

import com.example.expensetracker.data.local.entity.AccountDto
import com.example.expensetracker.domain.repository.TranscationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAccountsUseCase
@Inject constructor(
    private val transcationRepository: TranscationRepository
){

    operator fun invoke(): Flow<List<AccountDto>> {
        return transcationRepository.getAccounts()
    }

}