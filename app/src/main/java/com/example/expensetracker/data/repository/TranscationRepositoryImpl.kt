package com.example.expensetracker.data.repository

import com.example.expensetracker.data.TranscationDao
import com.example.expensetracker.data.local.entity.AccountDto
import com.example.expensetracker.data.local.entity.TranscationDto
import com.example.expensetracker.domain.repository.TranscationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TranscationRepositoryImpl
@Inject constructor(
    private val transcationDao: TranscationDao
): TranscationRepository{

    override suspend fun insertTranscation(dailyTranscation: TranscationDto) {
       transcationDao.insertTranscation(dailyTranscation)
    }

    override suspend fun insertAccount(accounts: List<AccountDto>) {
        transcationDao.inserAccount(accounts)
    }

    override fun getDailtTranscation(entryDate: String): Flow<List<TranscationDto>> {
       return transcationDao.getDailyTranscation(entryDate)
    }

    override fun getTranscationByAccountType(accountType: String): Flow<List<TranscationDto>> {
        return transcationDao.getTranscationByAccount(accountType)
    }

    override fun getAccount(account: String): Flow<AccountDto> {
        return transcationDao.getAccount(account)
    }

    override fun getAccounts(): Flow<List<AccountDto>> {
       return transcationDao.getAccounts()
    }

    override fun getAllTranscations(): Flow<List<TranscationDto>> {
        return transcationDao.getAllTranscations()
    }

    override fun eraseTranscation() {
        transcationDao.earseTranscation()
    }

    override fun getCurrentDayExpTranscation(): Flow<List<TranscationDto>> {
        return transcationDao.getCurrentDayExpenseTranscation()
    }

    override fun getWeeklyExpTranscation(): Flow<List<TranscationDto>> {
        return transcationDao.getWeeklyTranscation()
    }

    override fun getMonthlyExpTranscation(): Flow<List<TranscationDto>> {
       return transcationDao.getMonthlyExpTranscation()
    }

    override fun get3DayTranscation(transcationType: String): Flow<List<TranscationDto>> {
        return transcationDao.get3DayTranscation(transcationType)
    }

    override fun get7DayTranscation(transcationType: String): Flow<List<TranscationDto>> {
        return transcationDao.get7DayTranscation(transcationType)
    }

    override fun get14DayTranscation(transcationType: String): Flow<List<TranscationDto>> {
        return transcationDao.get14DayTranscation(transcationType)
    }

    override fun startOfTheMonthTranscation(transcationType: String): Flow<List<TranscationDto>> {
        return transcationDao.getStartOfMonthTranscation(transcationType)
    }

    override fun getLastMonthTranscation(transcationType: String): Flow<List<TranscationDto>> {
        return transcationDao.getLastMonthTranscation(transcationType)
    }

    override fun getTranscationByType(transcationType: String): Flow<List<TranscationDto>> {
        return transcationDao.getTranscationByType(transcation_type = transcationType )
    }
}