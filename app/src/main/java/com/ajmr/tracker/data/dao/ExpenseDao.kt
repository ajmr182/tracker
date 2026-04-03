package com.ajmr.tracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ajmr.tracker.data.entity.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM `Transaction`")
    fun getTransactions(): Flow<List<Transaction>>

    @Insert
    suspend fun insertTransactions(transaction: Transaction)
}