package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.common.topBar
import com.example.expensetracker.navigation.NavGraph.AppNavigation
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
                    topBar = {
                        topBar("Expenses")
                    },
                    bottomBar = {
                        NavigationBar {

                            val navBackStackEntry  by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            listOfNav.forEachIndexed {index , navItem ->
                                NavigationBarItem(
                                    selected = selectIndex == index,
                                    onClick = {
                                        selectIndex = index
                                        navController.navigate(navItem.route)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index==selectIndex){
                                                navItem.selectedIcon!!
                                            } else {
                                                navItem.unSelectedIcon!!
                                            } ,
                                            contentDescription =""
                                        )
                                    },
                                    label = {
                                        Text(text = navItem.title)
                                    }
                                )
                            }
                        }
                    }
                ) {innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(navController = navController)
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