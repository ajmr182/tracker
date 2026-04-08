package com.ajmr.tracker.ui.transaction

sealed class TransactionViewEffect {

    data class ShowError(val message: String) : TransactionViewEffect()
}