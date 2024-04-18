package com.example.expensetracker.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.expensetracker.navigation.Screens.BottomBarScreen
import com.example.expensetracker.ui.theme.TopAppBarBackground


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
            containerColor = TopAppBarBackground
        )
    )
}

val listOfNav = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Report,
    BottomBarScreen.Add,
    BottomBarScreen.Setting
)