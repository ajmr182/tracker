package com.ajmr.tracker.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajmr.tracker.data.entity.Expense
import com.ajmr.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    init {
        getExpenses()
    }

    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState: StateFlow<ExpenseUiState> = _uiState

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.OnSaveExpense -> saveExpense(event.expense)
        }
    }

    private fun getExpenses() = viewModelScope.launch {
        expenseRepository.getExpenses().collect { expenses ->
            _uiState.value = _uiState.value.copy(expenses = expenses)

        }
    }

    private fun saveExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.addExpense(expense)
    }
}