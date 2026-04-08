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

    object OnAddClicked : TransactionEvent

    object OnDismissAddDialog : TransactionEvent
}