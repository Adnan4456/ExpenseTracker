package com.example.expensetracker.domain.model

data class Currency(
    val country: String,
    val currencyCode: String = String(),
    val currencySymbol: String = String(),
)