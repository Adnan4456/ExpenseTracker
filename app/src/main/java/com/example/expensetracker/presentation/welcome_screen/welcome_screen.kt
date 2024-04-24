package com.example.expensetracker.presentation.welcome_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun welcome_screen (){
    
    val pagerstate  = rememberPagerState(pageCount = {
        4
    })

    //define Horizontal pager 
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        
        HorizontalPager(
            state = pagerstate,
            beyondBoundsPageCount = 3
        ) { page ->
            // Our page content
            Text(
                text = "Page: $page",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}