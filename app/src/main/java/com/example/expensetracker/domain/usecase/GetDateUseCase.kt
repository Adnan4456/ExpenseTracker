package com.example.expensetracker.domain.usecase

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class GetDateUseCase
@Inject constructor()
{
    private fun getDate(): String{
        return SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)
    }
}