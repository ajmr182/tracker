package com.ajmr.tracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.data.dao.ExpenseDao

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
}