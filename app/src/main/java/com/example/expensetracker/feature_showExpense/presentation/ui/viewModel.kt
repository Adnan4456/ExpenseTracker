package com.example.expensetracker.feature_showExpense.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.expensetracker.domain.usecase.GetDateFormattedUseCase
import com.example.expensetracker.domain.usecase.GetDateUseCase
import com.example.expensetracker.domain.usecase.read_database.GetCurrentDayExpTranscationUseCase
import com.example.expensetracker.domain.usecase.read_database.GetDailyTranscationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.security.PrivateKey
import java.util.*
import javax.inject.Inject

import com.example.expensetracker.domain.model.Transcation
@HiltViewModel
class HomeViewModel
    @Inject constructor(
     private val  getDateUseCase:GetDateUseCase,
     private val getFormattedDateUseCase: GetDateFormattedUseCase,
     private val getCurrentDayExpTranscationUseCase: GetCurrentDayExpTranscationUseCase,
     private val getDailyTransactionUseCase: GetDailyTranscationUseCase,
): ViewModel() {

    var currentTime = MutableStateFlow(Calendar.getInstance().time)
        private set

    var formattedDate = MutableStateFlow(String())
        private set

    var date = MutableStateFlow(String())
        private set

    var dailyTransaction = MutableStateFlow(emptyList<Transcation>())
        private set
    init {

        val currentDate = getDateUseCase()
//        Log.d("TAG","current date = ${currentDate}")
        formattedDate.value = getFormattedDateUseCase(currentTime.value)
        date.value = currentDate

        viewModelScope.launch {
            getDailyTransactionUseCase.invoke(currentDate).collect{
                it?.let {expense ->
                    Log.d("TAG","$expense")
                    dailyTransaction.value = expense.map {dailyExpense ->
                        dailyExpense.toTranscation()
                    }.reversed()
                }
            }
        }
    }
}