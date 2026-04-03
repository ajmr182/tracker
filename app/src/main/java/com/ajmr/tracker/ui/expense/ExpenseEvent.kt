package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Transaction

sealed interface ExpenseEvent {

    data class OnSaveExpense(val transaction: Transaction) : ExpenseEvent
}