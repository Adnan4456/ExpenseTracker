package com.example.expensetracker.presentation.account_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.expensetracker.presentation.account_screen.components.AccountItem
import com.example.expensetracker.utils.spacing
import com.example.expensetracker.presentation.navigation.Screen


@Composable
fun AccountScreen(
    navController: NavHostController,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val accounts by accountViewModel.allAccounts.collectAsState()
    val currency by accountViewModel.selectedCurrencyCode.collectAsState()
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            item {
                Text(
                    text = "Accounts",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W700),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.medium,
                            end = MaterialTheme.spacing.medium,
                            top = MaterialTheme.spacing.small
                        ),
                    textAlign = TextAlign.Start
                )

            }
            items(accounts) { account ->
                AccountItem(account, currency) { accountType ->
                    navController.navigate("${Screen.AccountDetailScreen.route}/$accountType")
                }
            }
        }
    }
}