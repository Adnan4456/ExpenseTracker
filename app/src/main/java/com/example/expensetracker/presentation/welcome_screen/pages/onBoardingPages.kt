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
        R.drawable.entry,"Add entries", "Keep track of your income and expenses"
    )

    class SecondPage:onBoardingPages(
        R.drawable.insight,"Check insights",
        "Detailed weekly and monthly charts based on your entries"
    )

    class ThirdPage : onBoardingPages(
        R.drawable.decision,"Make right decisions",
        "Control your money flow and stay on top of your game"
    )
}
