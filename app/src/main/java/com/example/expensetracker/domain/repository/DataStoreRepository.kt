package com.example.expensetracker.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    //check user  has done onBoard screen or not

    suspend fun writeOnBoardingKeyToDataStore(completed : Boolean)


    suspend fun readOnBoardingKeyfromDataStore(): Flow<Boolean>

    suspend fun writeCurrencyToDataStore(currency : String)

    suspend fun readCurrencyFromDataStore(): Flow<String>

    suspend fun writeExpenseLimitToDataStore(expense : Double)

    suspend fun readExpenseLimitFromDataStore(): Flow<Double>

    suspend fun writeLimitKeytoDataStore(enabled : Boolean)

    suspend fun readLimitKeytoDataStore():Flow<Boolean>

    suspend fun writeLimitDurationToDataStore(duration :Int)

    suspend fun readLimitDurationToDataStore() : Flow<Int>

    suspend fun eraseDataStore()
}