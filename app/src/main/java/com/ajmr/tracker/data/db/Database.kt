package com.ajmr.tracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajmr.tracker.data.entity.Expense
import com.ajmr.tracker.data.dao.ExpenseDao

@Database(
    entities = [Expense::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
}