package com.ajmr.tracker.domain.repository

import com.ajmr.tracker.data.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getTransactions(): Flow<List<Transaction>>

    suspend fun insertTransaction(transaction: Transaction)
}