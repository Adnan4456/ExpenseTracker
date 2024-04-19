package com.example.expensetracker.feature_showExpense.presentation.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.expensetracker.common.topBar


@Composable
fun  ExpensesScreen() {

    Scaffold(
        topBar = {
            topBar(title = "Expenses")
        }
    ) {
        Text(text = "Home expenses Screen")
    }
}