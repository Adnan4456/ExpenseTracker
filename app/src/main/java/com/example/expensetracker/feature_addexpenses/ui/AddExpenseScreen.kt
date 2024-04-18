package com.example.expensetracker.feature_addexpenses.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.expensetracker.common.topBar
import com.example.expensetracker.ui.theme.BackgroundElevated
import com.example.expensetracker.ui.theme.Destructive
import com.example.expensetracker.ui.theme.TextPrimary
import com.example.expensetracker.ui.theme.Typography
@Composable
fun AddExpenseScreen() {

    Scaffold(
        topBar = {
            topBar(title = "Add")
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundElevated
                ) ,
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 20.dp
                )


            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Amount",
                            style = Typography.bodyLarge,
                            color = TextPrimary)
                       Text(text ="150")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            },
                        text = "Recurrance" ,
                        style = Typography.bodyMedium,
                        color = TextPrimary
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            },
                        text = "Date" ,
                        style = Typography.bodyMedium,
                        color = TextPrimary
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            },
                        text = "Note" ,
                        style = Typography.bodyMedium,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            },
                        text = "Category" ,
                        style = Typography.bodyMedium,
                        color = TextPrimary
                    )

                }
            }
        }
    }

}