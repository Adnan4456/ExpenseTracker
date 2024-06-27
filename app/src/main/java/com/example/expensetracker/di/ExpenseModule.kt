package com.example.expensetracker.di

import android.content.Context
import androidx.annotation.Keep
import androidx.room.Room
import com.example.expensetracker.data.TransactionDatabase
import com.example.expensetracker.data.TranscationDao
import com.example.expensetracker.data.repository.DataStoreRepositoryImpl
import com.example.expensetracker.data.repository.TranscationRepositoryImpl
import com.example.expensetracker.domain.repository.DataStoreRepository
import com.example.expensetracker.domain.repository.TranscationRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExpenseModule {

    @Provides
    @Singleton
    fun provideDatastoreRepository(@ApplicationContext context: Context) : DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(transactionDao: TranscationDao) : TranscationRepository
            = TranscationRepositoryImpl(transactionDao)

    @Provides
    @Singleton
    fun provideExpenseDatabase(@ApplicationContext context: Context) : TransactionDatabase {
        return Room.databaseBuilder(context, TransactionDatabase::class.java, "transactionDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: TransactionDatabase) = database.transactionDao

}