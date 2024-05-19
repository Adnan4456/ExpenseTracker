package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.DrawerDefaults.scrimColor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.navigation.NavGraph.AppNavigation
import com.example.expensetracker.presentation.welcome_screen.welcome_screen
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                val navController = rememberNavController()
                var selectIndex by remember {
                    mutableStateOf(0)
                }

                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            showBottomSheet = true
                        }) {
                            Icon(imageVector =Icons.Default.Add ,
                                contentDescription = "" )
                        }
                    },
                    bottomBar = {
                        NavigationBar {

                        }
                    },
//                    floatingActionButtonPosition = FabPosition.Center,
                    ) {innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
//                        welcome_screen(navController)
                      AppNavigation(navController = navController)

                        if(showBottomSheet){
                            ModalBottomSheet(
                                modifier = Modifier.fillMaxHeight(0.3f),
                                onDismissRequest = {
                                    showBottomSheet = false
                                },
                                sheetState = sheetState,
                            ) {
                                //sheet content

                                Row (
                                    modifier = Modifier.fillMaxWidth()
                                        ){

                                    Button(onClick = {
                                        scope.launch {
                                            sheetState.hide()
                                        }.invokeOnCompletion {
                                            if (!sheetState.isVisible){
                                                showBottomSheet = false
                                            }
                                        }
                                    }) {

                                        Text(text = "Add Expense")
                                    }

                                    Button(onClick = {
                                        scope.launch {
                                            sheetState.hide()
                                        }.invokeOnCompletion {
                                            if (!sheetState.isVisible){
                                                showBottomSheet = false
                                            }
                                        }
                                    }) {

                                        Text(text = "Add Balance")
                                    }
                                }


                            }
                        }
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