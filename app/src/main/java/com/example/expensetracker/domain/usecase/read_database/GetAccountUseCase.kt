package com.example.expensetracker.domain.usecase.read_database

import com.example.expensetracker.data.local.model.entity.AccountDto
import com.example.expensetracker.domain.repository.TranscationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAccountUseCase
@Inject constructor(
    private val transcationRepository: TranscationRepository
){

    operator fun invoke(account : String): Flow<AccountDto> {
        return transcationRepository.getAccount(account)
    }

}