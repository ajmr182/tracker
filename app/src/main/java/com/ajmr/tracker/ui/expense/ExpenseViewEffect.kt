package com.ajmr.tracker.ui.expense

sealed class ExpenseViewEffect {

    data class ShowError(val message: String) : ExpenseViewEffect()
}