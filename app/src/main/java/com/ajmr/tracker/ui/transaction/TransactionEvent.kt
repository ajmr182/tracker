package com.ajmr.tracker.ui.transaction

import com.ajmr.tracker.domain.model.Categories

sealed interface TransactionEvent {

    data class OnSaveTransaction(
        val description: String,
        val amount: Double,
        val category: Categories,
    ) : TransactionEvent

    object OnAddClicked : TransactionEvent

    object OnDismissAddDialog : TransactionEvent
}