package com.ajmr.tracker.ui.transaction

import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.TransactionType

data class TransactionUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val incomes: List<Transaction> = emptyList(),
    val expenses: List<Transaction> = emptyList(),
    val transactionType: TransactionType? = null
)