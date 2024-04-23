package com.example.expensetracker.data

import androidx.room.*
import com.example.expensetracker.common.TranscationType
import com.example.expensetracker.data.local.entity.AccountDto
import com.example.expensetracker.data.local.entity.TranscationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TranscationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertTranscation(dailyTranscation: TranscationDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAccount(accounts: List<AccountDto>)

    @Query("SELECT * from transcation_table WHERE entry_date= :entryDate")
    fun getDailyTranscation(entryDate:String): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table WHERE account=:accountType")
    fun getTranscationByAccount(accountType: String): Flow<List<TranscationDto>>

    @Query("Select * from accounts_table where account = :account")
    fun getAccount(account:  String): Flow<AccountDto>


    @Query("SELECT * from accounts_table")
    fun getAccounts():Flow<List<AccountDto>>


    @Query("SELECT * from transcation_table")
    fun getAllTranscations(): Flow<List<TranscationDto>>


    @Query("Delete from transcation_table")
    fun earseTranscation()


    @Query("SELECT * from transcation_table where entry_date= date('now','localTime')  AND  transcation_type = :transcationType")
    fun getCurrentDayExpenseTranscation(transcationType: String =  TranscationType.EXPENSE.title): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table where entry_date BETWEEN date('now','-1 month')  AND  date('now','localTime') AND  transcation_type = :transcationType ")
    fun getMonthlyExpTranscation(transcationType: String  =  TranscationType.EXPENSE.title): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table where entry_date BETWEEN date('now','-7 day')  AND  date('now','localTime') AND  transcation_type = :transcationType ")
    fun getWeeklyTranscation(transcationType: String  =  TranscationType.EXPENSE.title): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table where entry_date >= date('now','-3 day')  AND  entry_date < date('now','localTime') AND  transcation_type = :transcationType ")
    fun get3DayTranscation(transcationType: String  ): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table where entry_date >= date('now','-7 day')  AND entry_date < date('now','localTime') AND  transcation_type = :transcationType ")
    fun get7DayTranscation(transcationType: String): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table where entry_date >= date('now','-14 day')  AND entry_date < date('now','localTime') AND  transcation_type = :transcationType ")
    fun get14DayTranscation(transcationType: String): Flow<List<TranscationDto>>

    @Query("SELECT * from transcation_table where transcation_type = :transcation_type ")
    fun getTranscationByType(transcation_type : String): Flow<List<TranscationDto>>

    @Query("Select * from transcation_table where entry_date >= date('now','start of month') AND entry_date < date('now','localTime') AND  transcation_type = :transcationType")
    fun getStartOfMonthTranscation(transcationType: String):Flow<List<TranscationDto>>

    @Query("Select * from transcation_table where entry_date >= date('now','-1 month') AND entry_date < date('now','localTime') AND  transcation_type = :transcationType")
    fun getLastMonthTranscation(transcationType: String):Flow<List<TranscationDto>>

}