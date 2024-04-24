package com.example.expensetracker.presentation.welcome_screen


import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.example.expensetracker.presentation.welcome_screen.pages.onBoardingPages

@Composable
fun welcome_screen (){


    //define Horizontal pager 
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        myPager()
//        PagerFromDocumentation()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun myPager(){

    val pagerScreen = listOf(
        onBoardingPages.FirstPage(),
        onBoardingPages.SecondPage(),
        onBoardingPages.ThirdPage(),
    )
    val pagerState = rememberPagerState {
            pagerScreen.size
    }

    HorizontalPager(
        state = pagerState) { pager ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = pagerScreen[pager].title)
        }
    }
}

