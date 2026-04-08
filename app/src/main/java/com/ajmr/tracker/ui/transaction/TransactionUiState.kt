package com.ajmr.tracker.ui.transaction

import com.ajmr.tracker.data.entity.Transaction

data class TransactionUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val incomes: List<Transaction> = emptyList(),
    val expenses: List<Transaction> = emptyList(),
    val showAddDialog: Boolean = false,
)