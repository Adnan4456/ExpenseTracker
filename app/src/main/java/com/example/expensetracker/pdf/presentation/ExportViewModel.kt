package com.example.expensetracker.pdf.presentation

// presentation/viewmodel/ExportViewModel.kt
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.pdf.domain.ExportRepository
import com.example.expensetracker.pdf.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExportViewModel @Inject constructor(
    private val exportRepository: ExportRepository
) : ViewModel() {

    private val _exportState = MutableStateFlow<ExportState>(ExportState.Idle)
    val exportState: StateFlow<ExportState> = _exportState

    fun exportTransactions() {
        _exportState.value = ExportState.Loading
        viewModelScope.launch {
            when (val result = exportRepository.exportTransactionsToPdf()) {
                is Result.Success -> {
                    _exportState.value = ExportState.Success(result.data)
                }
                is Result.Error -> {
                    _exportState.value = ExportState.Error(result.message)
                }
            }
        }
    }

    fun exportAccounts() {
        _exportState.value = ExportState.Loading
        viewModelScope.launch {
            when (val result = exportRepository.exportAccountsToPdf()) {
                is Result.Success -> {
                    _exportState.value = ExportState.Success(result.data)
                }
                is Result.Error -> {
                    _exportState.value = ExportState.Error(result.message)
                }
            }
        }
    }

    sealed class ExportState {
        object Idle : ExportState()
        object Loading : ExportState()
        data class Success(val fileUri: Uri) : ExportState()
        data class Error(val message: String) : ExportState()
    }
}