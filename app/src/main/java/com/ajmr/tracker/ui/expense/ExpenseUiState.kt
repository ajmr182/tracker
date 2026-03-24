package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Expense

data class ExpenseUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val expenses: List<Expense> = emptyList(),
)