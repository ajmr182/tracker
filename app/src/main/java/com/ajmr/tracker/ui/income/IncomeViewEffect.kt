package com.ajmr.tracker.ui.income

sealed class IncomeViewEffect {

    data class ShowError(val message: String) : IncomeViewEffect()
}