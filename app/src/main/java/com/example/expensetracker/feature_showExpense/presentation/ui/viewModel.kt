package com.example.expensetracker.feature_showExpense.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Transaction

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
import com.example.expensetracker.domain.usecase.read_database.GetAccountsUseCase
import com.example.expensetracker.domain.usecase.read_database.GetAllTranscationUseCase
import com.example.expensetracker.feature_showExpense.presentation.ui.component.Tabs
import kotlinx.coroutines.Dispatchers
import java.math.RoundingMode
import java.text.DecimalFormat

@HiltViewModel
class HomeViewModel
    @Inject constructor(
        private val  getDateUseCase:GetDateUseCase,
        private val getFormattedDateUseCase: GetDateFormattedUseCase,
        private val getCurrentDayExpTranscationUseCase: GetCurrentDayExpTranscationUseCase,
        private val getDailyTransactionUseCase: GetDailyTranscationUseCase,
        private val getAccountsUseCase: GetAccountsUseCase,
        private val getAllTransactionUseCase: GetAllTranscationUseCase,
): ViewModel() {


    var tabButton= MutableStateFlow(Tabs.TODAY)
        private set

    var selectedCurrencyCode = MutableStateFlow(String())
        private set

    var totalIncome = MutableStateFlow(0.0)
        private set

    var totalExpense = MutableStateFlow(0.0)
        private set

    var currentTime = MutableStateFlow(Calendar.getInstance().time)
        private set

    var formattedDate = MutableStateFlow(String())
        private set

    var date = MutableStateFlow(String())
        private set

    var dailyTransaction = MutableStateFlow(emptyList<Transcation>())
        private set

    var monthlyTransaction = MutableStateFlow(mapOf<String, List<Transaction>>())
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

        viewModelScope.launch(Dispatchers.IO) {
            getAccountsUseCase().collect { accountsDto ->
                val accounts = accountsDto.map { it.toAccount() }
                val income = calculateTransaction(accounts.map { it.income })
                val expense = calculateTransaction(accounts.map { it.expense })

                totalIncome.value = income
                totalExpense.value = expense
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            getAllTransactionUseCase().collect { allTransaction ->
                allTransaction?.let {
                  val sortedTrans =   allTransaction.map {
                        it.toTranscation()
                    }.reversed()
//                    monthlyTransaction.value = sortedTrans.groupBy{
//
//                    }
                }
            }
        }
    }

    private fun calculateTransaction(transactions: List<Double>): Double {
        return transactions.sumOf {
            it
        }
    }

    fun selectTab(button :Tabs){
        tabButton.value = button
    }
}
fun String.amountFormat(): String {

    //defining formator
    val amountFormatter = DecimalFormat("#,##0.00")
    //amountFormatter.roundingMode = RoundingMode.CEILING // we can also scale the amount
    return " " + amountFormatter.format(this.toDouble())
}