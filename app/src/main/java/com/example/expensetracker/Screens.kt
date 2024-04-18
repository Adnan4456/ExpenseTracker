package com.example.expensetracker

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.expensetracker.navigation.Screens.BottomBarScreen

@Composable
fun Home() {

    Text(text = "Home Screen")
}

@Composable
fun Add() {

    Text(text = "Add Screen")
}

@Composable
fun Setting() {

    Text(text = "Setting Screen")
}

@Composable
fun Report() {

    Text(text = "Report Screen")
}


val listOfNav = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Report,
    BottomBarScreen.Add,
    BottomBarScreen.Setting
)