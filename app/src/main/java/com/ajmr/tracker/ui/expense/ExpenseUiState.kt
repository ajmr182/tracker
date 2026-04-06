package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Transaction

data class ExpenseUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val expenses: List<Transaction> = emptyList(),
    val showAddDialog: Boolean = false,
)