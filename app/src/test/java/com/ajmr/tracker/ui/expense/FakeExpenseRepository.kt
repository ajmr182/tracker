package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Expense
import com.ajmr.tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeExpenseRepository : ExpenseRepository {

    private val flow = MutableSharedFlow<List<Expense>>(replay = 1)

    override fun getExpenses(): Flow<List<Expense>> = flow

    override suspend fun addExpense(expense: Expense) {}

    suspend fun emitExpenses(expenses: List<Expense>) {
        flow.emit(expenses)
    }
}