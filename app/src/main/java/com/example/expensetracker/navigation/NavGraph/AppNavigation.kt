package com.example.expensetracker.navigation.NavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.expensetracker.feature_report.ui.ReportScreen
import com.example.expensetracker.feature_setting.presentation.ui.SettingScreen
import com.example.expensetracker.feature_showExpense.presentation.ui.ExpensesScreen
import com.example.expensetracker.navigation.Screens.BottomBarScreen
import com.example.expensetracker.presentation.welcome_screen.welcome_screen


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
            ExpensesScreen()
        }

        composable(BottomBarScreen.Add.route){
//            AddExpenseScreen()
            welcome_screen(navController)
        }

        composable(BottomBarScreen.Setting.route){
            SettingScreen(navController)
        }

        composable(BottomBarScreen.Report.route){
            ReportScreen()
        }
        composable("category"){
//            CategoryScreen()
        }
    }

}