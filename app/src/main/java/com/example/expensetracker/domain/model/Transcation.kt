package com.example.expensetracker.domain.model

import java.util.Date

data class Transcation(
    val date: Date,
    val dateOfEntry: String,
    val amount: Double,
    val category: String,
    val account: String,
    val transcationType: String,
    val title: String
)
