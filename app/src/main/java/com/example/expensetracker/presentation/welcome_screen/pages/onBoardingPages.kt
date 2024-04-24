package com.example.expensetracker.presentation.welcome_screen.pages

import androidx.annotation.DrawableRes
import com.example.expensetracker.R

sealed class onBoardingPages(
    @DrawableRes
    val icon : Int,
    val title : String,
    val description: String
){
     class FirstPage:onBoardingPages(
        R.drawable.blank_list,"First Page","Swipe "
    )

    class SecondPage:onBoardingPages(
        R.drawable.blank_list,"Second Page","Swipe"
    )

    class ThirdPage : onBoardingPages(
        R.drawable.blank_list,"Third Page","Swipe"
    )
}
