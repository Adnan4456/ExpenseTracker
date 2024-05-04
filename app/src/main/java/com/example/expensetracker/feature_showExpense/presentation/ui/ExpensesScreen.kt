package com.example.expensetracker.feature_showExpense.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.common.topBar


@Composable
fun  ExpensesScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    viewModel.formattedDate
    Scaffold(
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text =     viewModel.formattedDate.value)

        }
    }
}