package com.ajmr.tracker.ui.expense

import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeTransactionRepository : TransactionRepository {

    private val flow = MutableSharedFlow<List<Transaction>>(replay = 1)

    override fun getTransactions(): Flow<List<Transaction>> = flow

    override suspend fun insertTransaction(transaction: Transaction) {}

    suspend fun emitExpenses(expenses: List<Transaction>) {
        flow.emit(expenses)
    }
}