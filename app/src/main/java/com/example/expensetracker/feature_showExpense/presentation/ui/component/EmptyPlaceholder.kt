package com.example.expensetracker.feature_showExpense.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.expensetracker.utils.spacing
import com.example.expensetracker.R

@Composable
fun EmptyPlaceholder(
    label: String =
        "No transaction has been made so far. Tap the '+' button to  get started"
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.medium,
            top = MaterialTheme.spacing.medium,
            end = MaterialTheme.spacing.medium
        ).fillMaxSize()
    ) {
        Icon(
            painter = painterResource(R.drawable.blank_list),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = "no item added"
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

}