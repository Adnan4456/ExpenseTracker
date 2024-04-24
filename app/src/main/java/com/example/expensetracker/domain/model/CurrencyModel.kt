package com.example.expensetracker.domain.model

data class CurrencyModel(
    val country: String,
    val currencyCode: String = String(),
    val currencySymbol: String = String(),
)