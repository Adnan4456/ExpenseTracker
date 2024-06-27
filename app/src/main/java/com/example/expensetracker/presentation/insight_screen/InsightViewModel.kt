package com.example.expensetracker.presentation.insight_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.model.Transcation
import com.example.expensetracker.domain.usecase.read_database.Get14DayTranscation
import com.example.expensetracker.domain.usecase.read_database.Get3DayTranscation
import com.example.expensetracker.domain.usecase.read_database.Get7DayTranscation
import com.example.expensetracker.domain.usecase.read_database.GetLastMonthTranscationUseCase
import com.example.expensetracker.domain.usecase.read_database.GetStartOfMonthTranscationUseCase
import com.example.expensetracker.domain.usecase.read_database.GetTranscationByTypeUseCase
import com.example.expensetracker.domain.usecase.read_datastore.GetCurrencyUseCase
import com.example.expensetracker.presentation.home_screen.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsightViewModel @Inject constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val get3DayTransaction: Get3DayTranscation,
    private val get7DayTransaction: Get7DayTranscation,
    private val get14DayTransaction: Get14DayTranscation,
    private val getStartOfMonthTransaction: GetStartOfMonthTranscationUseCase,
    private val getLastMonthTransaction: GetLastMonthTranscationUseCase,
    private val getAllTransaction: GetTranscationByTypeUseCase
) : ViewModel() {

    private var _tabButton = MutableStateFlow(TransactionType.INCOME)
    val tabButton: StateFlow<TransactionType> = _tabButton

    private var _filteredTransaction = MutableStateFlow(emptyList<Transcation>())
    val filteredTransaction: StateFlow<List<Transcation>> = _filteredTransaction

    var selectedCurrencyCode = MutableStateFlow(String())
        private set

    fun selectTabButton(tab: TransactionType) {
        _tabButton.value = tab
        getFilteredTransaction()
    }

    init {
        getFilteredTransaction()
        currencyFormat()
    }

    private fun currencyFormat() {
        viewModelScope.launch(IO) {
            getCurrencyUseCase().collect { selectedCurrency ->
                selectedCurrencyCode.value = selectedCurrency
            }
        }
    }

     fun getFilteredTransaction(duration: Int = 5) {
        viewModelScope.launch(IO) {
            if (_tabButton.value == TransactionType.INCOME) {
                filterTransaction(duration, TransactionType.INCOME.title)
            } else {
                filterTransaction(duration, TransactionType.EXPENSE.title)
            }
        }
    }

    private suspend fun filterTransaction(duration: Int, transactionType: String = TransactionType.INCOME.title) {
        when (duration) {
            0 -> {
                get3DayTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults.map { it.toTranscation() }
                }
            }
            1 -> {
                get7DayTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults.map { it.toTranscation() }
                }
            }
            2 -> {
                get14DayTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults.map { it.toTranscation() }
                }
            }
            3 -> {
                getStartOfMonthTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults.map { it.toTranscation() }
                }
            }
            4 -> {
                getLastMonthTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults.map { it.toTranscation() }
                }
            }
            5 -> {
                getAllTransaction(transactionType).collectLatest { filteredResults ->
                    _filteredTransaction.value = filteredResults.map { it.toTranscation() }
                }
            }
        }
    }
}