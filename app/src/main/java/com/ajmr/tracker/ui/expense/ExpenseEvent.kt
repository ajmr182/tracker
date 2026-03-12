package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Expense

sealed interface ExpenseEvent {

    data class OnSaveExpense(val expense: Expense) : ExpenseEvent
}