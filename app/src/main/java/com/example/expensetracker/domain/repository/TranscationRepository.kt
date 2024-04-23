package com.example.expensetracker.domain.repository

import com.example.expensetracker.data.local.model.entity.AccountDto
import com.example.expensetracker.data.local.model.entity.TranscationDto
import kotlinx.coroutines.flow.Flow

interface TranscationRepository {

    suspend fun  insertTranscation(dailyTranscation : TranscationDto)

    suspend fun insertAccount(accounts: List<AccountDto>)

    fun getDailtTranscation(entryDate : String): Flow<List<TranscationDto>>

    fun getTranscationByAccountType(accountType: String): Flow<List<TranscationDto>>

    fun getAccount(account: String):Flow<AccountDto>

    fun getAccounts(): Flow<List<AccountDto>>

    fun getAllTranscations():Flow<List<TranscationDto>>

    fun eraseTranscation()

    fun getCurrentDayExpTranscation():Flow<List<TranscationDto>>

    fun getWeeklyExpTranscation():Flow<List<TranscationDto>>

    fun getMonthlyExpTranscation():Flow<List<TranscationDto>>

    fun get3DayTranscation(transcationType: String):Flow<List<TranscationDto>>

    fun get7DayTranscation(transcationType: String):Flow<List<TranscationDto>>

    fun get14DayTranscation(transcationType: String):Flow<List<TranscationDto>>

    fun startOfTheMonthTranscation(transcationType: String):Flow<List<TranscationDto>>

    fun getLastMonthTranscation(transcationType: String):Flow<List<TranscationDto>>

    fun getTranscationByType(transcationType: String) : Flow<List<TranscationDto>>
}