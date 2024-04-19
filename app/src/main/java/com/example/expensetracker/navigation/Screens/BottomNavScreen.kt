package com.example.expensetracker.navigation.Screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val title: String,
    val selectedIcon: ImageVector? = null,
    val unSelectedIcon: ImageVector? = null,
    val route:String
){

    object Home: BottomBarScreen("Home" ,
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
    route = "home_screen")

    object Add: BottomBarScreen("Add" ,
        selectedIcon = Icons.Filled.Add,
        unSelectedIcon = Icons.Outlined.Add,
        route = "add_screen")

    object Setting: BottomBarScreen("Setting",
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings,
        route = "setting_screen")


    object Report: BottomBarScreen(
        title="Reports" ,
        selectedIcon = Icons.Filled.BarChart,
        unSelectedIcon = Icons.Outlined.BarChart,
        route = "report_screen"
    )
}