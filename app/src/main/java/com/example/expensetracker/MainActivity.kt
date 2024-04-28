package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.common.listOfNav
import com.example.expensetracker.navigation.NavGraph.AppNavigation
import com.example.expensetracker.presentation.welcome_screen.welcome_screen
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                val navController = rememberNavController()
                var selectIndex by remember {
                    mutableStateOf(0)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        NavigationBar {
//
//                            listOfNav.forEachIndexed {index , navItem ->
//                                NavigationBarItem(
//                                    selected = selectIndex == index,
//                                    onClick = {
//                                        selectIndex = index
//                                        navController.navigate(navItem.route){
//
//                                            popUpTo(navController.graph.findStartDestination().id) {
//                                                saveState = true
//                                            }
//
//                                            launchSingleTop = true
//                                            restoreState = true
//
//                                        }
//                                    },
//                                    icon = {
//                                        Icon(
//                                            imageVector = if (index==selectIndex){
//                                                navItem.selectedIcon!!
//                                            } else {
//                                                navItem.unSelectedIcon!!
//                                            },
//                                            contentDescription =""
//                                        )
//                                    },
//                                    label = {
//                                        Text(text = navItem.title)
//                                    }
//                                )
//                            }
//                        }
//                    }
                ) {innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        welcome_screen()
                    //  AppNavigation(navController = navController)
                    }

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpenseTrackerTheme {
    }
}