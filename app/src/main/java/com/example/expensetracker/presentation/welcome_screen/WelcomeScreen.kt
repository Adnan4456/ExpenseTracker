package com.example.expensetracker.presentation.welcome_screen

import android.view.Surface
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expensetracker.presentation.navigation.Screen
import com.example.expensetracker.presentation.welcome_screen.components.GetStartedButton
import com.example.expensetracker.presentation.welcome_screen.components.PagerScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages by welcomeViewModel.listOfPages
    val pagerState = rememberPagerState(
        pageCount = {
            pages.size
        }
    )

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(

                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(10f)
            ) { pageCount ->
                PagerScreen(page = pages[pageCount])
            }
//            HorizontalPagerIndicator(
//                pagerState = pagerState,
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .weight(1f),
//                indicatorWidth = 18.dp,
//                indicatorHeight = 4.dp,
//                activeColor = MaterialTheme.colorScheme.primary,
//                inactiveColor = Color.LightGray
//            )
            GetStartedButton(pagerState = pagerState, modifier = Modifier.weight(2f)) {
                navController.popBackStack()
                navController.navigate("${Screen.CurrencyScreen.route}/${false}")
            }
        }
    }
}