package com.example.expensetracker.presentation.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.expensetracker.utils.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){

    // expense entries
    val dailyTransactions by homeViewModel.dailyTransaction.collectAsState()
    val monthlyTransactions by homeViewModel.monthlyTransaction.collectAsState()
    val currentTabButton by homeViewModel.tabButton.collectAsState()


    val lazyListState = rememberLazyListState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )


    BottomSheetScaffold(
        sheetContent = { AddEntryChooser(bottomSheetScaffoldState, navController) },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContentColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // header
            Header(bottomSheetScaffoldState)

            // Button tabs
            TabButton()

            // daily expenses

            AnimatedVisibility(visible = currentTabButton == com.kuluruvineeth.expensetracker.presentation.home_screen.TabButton.TODAY) {
                dailyTransactions.ifEmpty {
                    ListPlaceholder()
                }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(
                        start = MaterialTheme.spacing.medium,
                        top = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.medium
                    )
                ) {
                    itemsIndexed(dailyTransactions) { pos, dailyTransaction ->
                        TransactionItem(
                            transaction = dailyTransaction,
                            onItemClick = {
                                val trxType = dailyTransaction.transactionType
                                if (trxType == TransactionType.INCOME.title)
                                    navController.navigate(
                                        "${Screen.TransactionScreen.route}/0?trxPos=${pos}&trxStatus=${0}"
                                    )
                                else
                                    navController.navigate(
                                        "${Screen.TransactionScreen.route}/1?trxPos=${pos}&trxStatus=${0}"
                                    )
                            }
                        )
                    }
                }
            }

            // monthly expenses
            AnimatedVisibility(visible = currentTabButton == com.kuluruvineeth.expensetracker.presentation.home_screen.TabButton.MONTH) {
                monthlyTransactions.ifEmpty {
                    ListPlaceholder()
                }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .background(MaterialTheme.colors.background),
                    contentPadding = PaddingValues(
                        start = MaterialTheme.spacing.medium,
                        top = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.medium
                    )
                ) {
                    monthlyTransactions.forEach { (date, monthlyTransaction) ->
                        stickyHeader {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(
                                        horizontal = MaterialTheme.spacing.medium,
                                        vertical = MaterialTheme.spacing.small
                                    ),
                            ) {
                                Text(
                                    text = date,
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }

                        itemsIndexed(monthlyTransaction) { pos, transaction ->
                            TransactionItem(
                                transaction = transaction,
                                onItemClick = {
                                    val trxType = transaction.transactionType
                                    if (trxType == TransactionType.INCOME.title)
                                        navController.navigate(
                                            "${Screen.TransactionScreen.route}/0?trxKey=${date}&trxPos=${pos}&trxStatus=${1}"
                                        )
                                    else
                                        navController.navigate(
                                            "${Screen.TransactionScreen.route}/1?trxKey=${date}&trxPos=${pos}&trxStatus=${1}"
                                        )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}