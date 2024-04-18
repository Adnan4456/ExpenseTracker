package com.example.expensetracker.feature_addexpenses.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.expensetracker.common.topBar

@Composable
fun AddExpenseScreen() {

    Scaffold(
        topBar = {
            topBar(title = "Add")
        }
    ) {
        Text(text = "Add Expense Screen")
    }

}