package com.example.expensetracker.feature_setting.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.expensetracker.common.topBar
import com.example.expensetracker.ui.theme.BackgroundElevated


@Composable
fun SettingScreen() {

Scaffold(
    topBar = {
        topBar(title = "Setting")
    }
) {innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                BackgroundElevated
            )
//            .border(
//               width = 1.dp,
//               shape = RoundedCornerShape(10.dp)
//            )
        ) {
            Text(text = "Categories")
            Text(text = "Delete all" ,
            style = TextStyle(
                color = Color.Red
            ))
        }
    }
}
}