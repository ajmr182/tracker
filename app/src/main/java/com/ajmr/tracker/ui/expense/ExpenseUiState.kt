package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Expense

data class ExpenseUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val expenses: List<Expense> = emptyList(),
)