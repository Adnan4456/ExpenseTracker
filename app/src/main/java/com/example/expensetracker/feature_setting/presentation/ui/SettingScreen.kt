package com.example.expensetracker.feature_setting.presentation.ui

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
import androidx.navigation.NavHostController
import com.example.expensetracker.common.topBar
import com.example.expensetracker.ui.theme.BackgroundElevated
import com.example.expensetracker.ui.theme.Destructive
import com.example.expensetracker.ui.theme.Typography
import com.example.expensetracker.ui.theme.TextPrimary

@Composable
fun SettingScreen(
    navController: NavHostController
) {

Scaffold(
    topBar = {
        topBar(title = "Settings")
    }
) {innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
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
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("category")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Categories",
                    style = Typography.bodyLarge,
                    color = TextPrimary)
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowRight, contentDescription = "")
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.White.copy(.1f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {

                        },
                    text = "Delete all" ,
                    style = Typography.bodyMedium,
                    color = Destructive
                )
            }
        }
    }
}
}