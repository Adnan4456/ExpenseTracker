package com.example.expensetracker.domain.usecase

import android.text.format.DateFormat
import java.util.Date
import javax.inject.Inject

open class GetDateFormattedUseCase
@Inject constructor(){

    open operator fun invoke(date : Date):  String{
        return getFormattedDate(date)
    }

    private fun getFormattedDate(date: Date):String{

        val dayofWeek  = DateFormat.format("EEE",date)
        val day = DateFormat.format("dd", date)
        val monthAttr = DateFormat.format("MMM",date)

        return  "$dayofWeek $day, $monthAttr"
    }

}