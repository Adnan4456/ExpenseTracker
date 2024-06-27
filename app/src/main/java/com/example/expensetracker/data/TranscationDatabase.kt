package com.example.expensetracker.data


import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensetracker.data.local.convertors.DateConvertor
import com.example.expensetracker.data.local.entity.AccountDto
import com.example.expensetracker.data.local.entity.TranscationDto


@TypeConverters(value = [DateConvertor::class])
@Database(entities = [TranscationDto::class, AccountDto::class], exportSchema = true, version = 1)
@Keep
abstract class TransactionDatabase: RoomDatabase() {
    abstract val transactionDao: TranscationDao
}