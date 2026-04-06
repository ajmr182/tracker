package com.ajmr.tracker.ui.income

import com.ajmr.tracker.data.entity.Transaction

data class IncomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val incomes: List<Transaction> = emptyList(),
    val showAddDialog: Boolean = false,
)