package com.ajmr.tracker.domain.model

import com.ajmr.tracker.R

enum class TransactionType(val label: Int) {
    INCOME(label = R.string.income),
    EXPENSE(label = R.string.expense),
}