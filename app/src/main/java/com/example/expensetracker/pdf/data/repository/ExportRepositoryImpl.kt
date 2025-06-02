package com.example.expensetracker.pdf.data.repository

import android.content.Context
import android.net.Uri
import com.example.expensetracker.data.TranscationDao
import com.example.expensetracker.data.*
import com.example.expensetracker.pdf.data.PdfGenerator
import com.example.expensetracker.pdf.domain.ExportRepository
import com.example.expensetracker.pdf.domain.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList

// data/repository/ExportRepositoryImpl.kt
class ExportRepositoryImpl(
    private val context: Context,
    private val transactionDao: TranscationDao,
    private val pdfGenerator: PdfGenerator
) : ExportRepository {

    override suspend fun exportTransactionsToPdf(): com.example.expensetracker.pdf.domain.Result<Uri> {
        return try {
            val transactions = transactionDao.getAllTranscations().first()
            if (transactions == null) {
                Result.Error("No transactions found")
            } else {
                Result.Success(pdfGenerator.generateTransactionsReport(transactions))
            }
        } catch (e: Exception) {
            Result.Error("Failed to export transactions: ${e.message}")
        }
    }

    override suspend fun exportAccountsToPdf(): Result<Uri> {
        return try {
            val accounts = transactionDao.getAccounts().first()
            if (accounts == null) {
                Result.Error("No accounts found")
            } else {
                Result.Success(pdfGenerator.generateAccountsReport(accounts))
            }
        } catch (e: Exception) {
            Result.Error("Failed to export accounts: ${e.message}")
        }
    }

    override suspend fun exportAllToPdf(): Result<Uri> {
        // You could implement a combined report here
        // For now, we'll just export transactions
        return exportTransactionsToPdf()
    }
}