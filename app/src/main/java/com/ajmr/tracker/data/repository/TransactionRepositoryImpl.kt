package com.ajmr.tracker.data.repository

import com.ajmr.tracker.data.dao.ExpenseDao
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val dao: ExpenseDao): TransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> = dao.getTransactions()

    override suspend fun insertTransaction(transaction: Transaction) = dao.insertTransactions(transaction)
}