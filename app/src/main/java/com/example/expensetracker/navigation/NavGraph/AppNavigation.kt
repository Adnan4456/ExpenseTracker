package com.example.expensetracker.navigation.NavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.feature_addexpenses.ui.AddExpenseScreen
import com.example.expensetracker.feature_categories.presentation.ui.CategoryScreen
import com.example.expensetracker.feature_report.ui.ReportScreen
import com.example.expensetracker.feature_setting.presentation.ui.SettingScreen
import com.example.expensetracker.feature_showExpense.presentation.ui.ExpensesScreen
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
            ExpensesScreen()
        }

        composable(BottomBarScreen.Add.route){
            AddExpenseScreen()
        }

        composable(BottomBarScreen.Setting.route){
            SettingScreen(navController)
        }

        composable(BottomBarScreen.Report.route){
            ReportScreen()
        }
        composable("category"){
            CategoryScreen()
        }
    }

}