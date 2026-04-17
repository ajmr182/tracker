package com.ajmr.tracker.ui.transaction

import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType

sealed interface TransactionEvent {

    data class OnSaveTransaction(
        val description: String,
        val amount: Double,
        val transactionType: TransactionType,
        val category: Categories,
    ) : TransactionEvent

    data class OnAddClicked(val transactionType: TransactionType) : TransactionEvent

    object OnDismissAddDialog : TransactionEvent
}