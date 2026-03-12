package com.ajmr.tracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ajmr.tracker.data.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM Expense")
    fun getExpenses(): Flow<List<Expense>>

    @Insert
    suspend fun insertExpense(expense: Expense)
}