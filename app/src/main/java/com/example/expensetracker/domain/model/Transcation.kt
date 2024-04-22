package com.example.expensetracker.domain.model

import java.util.Date

data class Transcation(
    val date: Date,
    val dateOfEntry: String,
    val amount: Double,
    val category: String,
    val account: String,
    val transcationType: String, // income or expense
    val title: String
)
