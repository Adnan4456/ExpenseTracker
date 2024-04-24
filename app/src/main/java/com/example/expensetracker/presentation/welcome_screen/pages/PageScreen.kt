package com.example.expensetracker.presentation.welcome_screen.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun PageScreen(
    page: onBoardingPages
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = page.icon),
            contentDescription = "")
    }
}