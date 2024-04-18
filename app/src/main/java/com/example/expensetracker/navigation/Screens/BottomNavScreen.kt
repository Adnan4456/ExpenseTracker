package com.example.expensetracker.navigation.Screens

import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val title: String,
    val icon: ImageVector? = null,
    val route:String
){

    object Report: BottomBarScreen("Report" , route = "report_screen")

    object Add: BottomBarScreen("Add" , route = "add_screen")

    object Setting: BottomBarScreen("Setting", route = "setting_screen")

}