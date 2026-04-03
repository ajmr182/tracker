package com.ajmr.tracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajmr.tracker.domain.model.TransactionType

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: String,
    val transactionType: TransactionType,
    val description: String,
    val date: Long,
)