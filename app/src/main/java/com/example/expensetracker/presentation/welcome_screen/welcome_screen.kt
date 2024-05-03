package com.example.expensetracker.presentation.welcome_screen


import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.presentation.welcome_screen.pages.PageScreen

import com.example.expensetracker.presentation.welcome_screen.pages.onBoardingPages

@Composable
fun welcome_screen (viewModel:WelcomeViewModel = hiltViewModel()){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        myPager(viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun myPager(
    viewModel:WelcomeViewModel
){

//    val pagerScreen = listOf(
//        onBoardingPages.FirstPage(),
//        onBoardingPages.SecondPage(),
//        onBoardingPages.ThirdPage(),
//    )

    val pages by viewModel.listOfPages

    val pagerState = rememberPagerState {
        pages.size
    }

    HorizontalPager(
        state = pagerState
    ) { pager ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            PageScreen(pages[pager])
        }
    }
}

