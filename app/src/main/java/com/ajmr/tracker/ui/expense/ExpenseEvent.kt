package com.ajmr.tracker.ui.expense

sealed interface ExpenseEvent {

    data class OnSaveExpense(val description: String, val amount: Double, val category: String) : ExpenseEvent

    object OnAddClicked : ExpenseEvent

    object OnDismissAddDialog : ExpenseEvent
}