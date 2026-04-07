package com.ajmr.tracker.ui.income

import com.ajmr.tracker.domain.model.Categories

sealed interface IncomeEvent {

    data class OnSaveIncome(
        val description: String,
        val amount: Double,
        val category: Categories,
    ) : IncomeEvent

    object OnAddClicked : IncomeEvent

    object OnDismissAddDialog : IncomeEvent
}