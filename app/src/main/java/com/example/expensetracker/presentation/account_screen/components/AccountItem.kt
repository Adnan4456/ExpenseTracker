package com.example.expensetracker.presentation.account_screen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetracker.domain.model.Account
import com.example.expensetracker.presentation.home_screen.amountFormat
import com.example.expensetracker.util.spacing
import com.example.expensetracker.utils.spacing
import com.example.expensetracker.presentation.home_screen.Account as AccountType


@Composable
fun AccountItem(account: Account, currency: String, onItemClick: (String) -> Unit) {
    Card(
        onClick = {
            onItemClick(account.account)
        },
        backgroundColor = Color.DarkGray.copy(alpha = 0.1f),
        elevation = 0.dp,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
//                    top = MaterialTheme.spacing.small
                )
        ) {
            Text(
                text = "Balance",
//                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.medium
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        letterSpacing = 0.4.sp
                    )
                ) {
                    append(currency)
                }
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 28.sp,
                        letterSpacing = 0.25.sp
                    )
                ) {
                    append(account.amount.toString().amountFormat())
                }
            }, modifier = Modifier.padding(start = MaterialTheme.spacing.medium))

            val color = when (account.account) {
                AccountType.CASH.title -> AccountType.CASH.color
                AccountType.CARD.title -> AccountType.CARD.color
                else -> AccountType.BANK.color
            }

            Surface(
                color = color,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacing.small)
                    ) {
                        Text(
                            text = account.account,
//                            style = MaterialTheme.typography.caption,
                            color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.medium)
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    letterSpacing = 0.4.sp,
                                    color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
                                )
                            ) {
                                append(currency)
                            }
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Thin,
                                    fontSize = 18.sp,
                                    letterSpacing = 0.25.sp,
                                    color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
                                )
                            ) {
                                append(account.income.toString().amountFormat())
                            }
                        })

                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Thin,
                                    fontSize = 10.sp,
                                    letterSpacing = 0.4.sp,
                                    color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
                                )
                            ) {
                                append(currency)
                            }
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Thin,
                                    fontSize = 18.sp,
                                    letterSpacing = 0.25.sp,
                                    color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
                                )
                            ) {
                                append(account.expense.toString().amountFormat())
                            }
                        })
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.medium)
                    ) {
                        Text(
                            text = "INCOME",
//                            style = MaterialTheme.typography.overline,
//                            color = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                        )

                        Text(
                            text = "EXPENSE",
//                            style = MaterialTheme.typography.overline,
//                            color = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                        )
                    }
                }
            }
        }
    }
}