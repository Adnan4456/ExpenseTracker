package com.example.expensetracker.feature_showExpense.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.expensetracker.domain.usecase.GetDateFormattedUseCase
import com.example.expensetracker.domain.usecase.GetDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
    @Inject constructor(
     val  getDateUseCase:GetDateUseCase,
     val getFormattedDateUseCase: GetDateFormattedUseCase,
): ViewModel() {

    var currentTime = MutableStateFlow(Calendar.getInstance().time)
        private set

    var formattedDate = MutableStateFlow(String())
        private set

    var date = MutableStateFlow(String())
        private set

    init {

        val currentDate = getDateUseCase()
        Log.d("TAG","current date = ${currentDate}")
        formattedDate.value = getFormattedDateUseCase(currentTime.value)
        Log.d("TAG","formattedDate  = ${formattedDate.value}")
        Log.d("TAG","Current Time  = ${currentTime.value}")

        date.value = currentDate


    }
}