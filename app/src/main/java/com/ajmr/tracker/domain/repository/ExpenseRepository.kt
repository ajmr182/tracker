package com.ajmr.tracker.domain.repository

import com.ajmr.tracker.data.entity.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    fun getExpenses(): Flow<List<Expense>>

    suspend fun addExpense(expense: Expense)
}