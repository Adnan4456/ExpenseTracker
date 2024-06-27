package com.example.expensetracker.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.presentation.navigation.components.BottomNavBar
import com.example.expensetracker.presentation.navigation.components.provideBottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    startDestination: String
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    val navController = rememberNavController()
    val scaffoldState = rememberBottomSheetScaffoldState(
//        ModalBottomSheetValue.Hidden
    )

    val rootDestinations = listOf(
        Screen.HomeScreen.route,
        Screen.InsightScreen.route,
        Screen.AccountScreen.route,
        Screen.SettingScreen.route
    )

    val bottomNavBarState = remember { mutableStateOf(true) }

    val navBarEntry by navController.currentBackStackEntryAsState()
    bottomNavBarState.value = rootDestinations.contains(navBarEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomNavBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavBar(
                    items = provideBottomNavItems(), navController
                ) {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        },
//        scaffoldState = scaffoldState
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(
                bottom = it.calculateBottomPadding()
            )
        ) {
            MainNavigation(
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}