package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.domain.model.Categories

sealed interface ExpenseEvent {

    data class OnSaveExpense(
        val description: String,
        val amount: Double,
        val category: Categories,
    ) : ExpenseEvent

    object OnAddClicked : ExpenseEvent

    object OnDismissAddDialog : ExpenseEvent
}