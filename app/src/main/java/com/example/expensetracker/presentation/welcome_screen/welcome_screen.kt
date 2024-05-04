package com.example.expensetracker.presentation.welcome_screen


import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.expensetracker.presentation.welcome_screen.pages.PageScreen

import com.example.expensetracker.presentation.welcome_screen.pages.onBoardingPages

@Composable
fun welcome_screen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        myPager(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun myPager(navController: NavHostController) {

    val pagerScreen = listOf(
        onBoardingPages.FirstPage(),
        onBoardingPages.SecondPage(),
        onBoardingPages.ThirdPage(),
    )

//    val pages by viewModel.listOfPages

    val pagerState = rememberPagerState {
        pagerScreen.size
    }

    Column() {
        HorizontalPager(
            state = pagerState
        ) { pager ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                PageScreen(pagerScreen[pager])
            }
        }
        NextButton()   {
          navController.popBackStack()
//          navController.navigate()
        }
    }

}

@Composable
fun NextButton(onclick: () -> Unit){

    Button(onClick = {
        onclick
    }) {

    }
}

