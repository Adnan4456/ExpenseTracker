package com.example.expensetracker.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(title: String) {

    MediumTopAppBar(
        title = {
            Text(text = "${title}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                )
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Black
        )
    )
}