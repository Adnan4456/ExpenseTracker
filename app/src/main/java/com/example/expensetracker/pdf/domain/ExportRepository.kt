package com.example.expensetracker.pdf.domain

import android.net.Uri

interface ExportRepository {
    suspend fun exportTransactionsToPdf(): Result<Uri>
    suspend fun exportAccountsToPdf(): Result<Uri>
    suspend fun exportAllToPdf(): Result<Uri>
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}