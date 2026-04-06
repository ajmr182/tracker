package com.ajmr.tracker.ui.income

sealed interface IncomeEvent {

    data class OnSaveIncome(val description: String, val amount: Double, val category: String) : IncomeEvent

    object OnAddClicked : IncomeEvent

    object OnDismissAddDialog : IncomeEvent
}