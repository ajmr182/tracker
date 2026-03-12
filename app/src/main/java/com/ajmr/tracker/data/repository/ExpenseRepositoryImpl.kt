package com.ajmr.tracker.data.repository

import com.ajmr.tracker.data.dao.ExpenseDao
import com.ajmr.tracker.data.entity.Expense
import com.ajmr.tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(private val dao: ExpenseDao): ExpenseRepository {

    override fun getExpenses(): Flow<List<Expense>> = dao.getExpenses()

    override suspend fun addExpense(expense: Expense) = dao.insertExpense(expense)
}