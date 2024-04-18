package com.example.expensetracker.feature_report.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.expensetracker.common.topBar


@Composable
fun ReportScreen() {
    Scaffold(
        topBar = {
            topBar(title = "Reports")
        }
    ) {
        Text(text = "Reports Screen")
    }

}