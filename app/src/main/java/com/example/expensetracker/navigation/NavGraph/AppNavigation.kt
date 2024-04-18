package com.example.expensetracker.navigation.NavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.Add
import com.example.expensetracker.Home
import com.example.expensetracker.Report
import com.example.expensetracker.Setting
import com.example.expensetracker.navigation.Screens.BottomBarScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
)
{
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){
        composable(BottomBarScreen.Home.route){
            Home()
        }

        composable(BottomBarScreen.Add.route){
            Add()
        }

        composable(BottomBarScreen.Setting.route){
            Setting()
        }

        composable(BottomBarScreen.Report.route){
            Report()
        }
    }

}