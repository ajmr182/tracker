package com.ajmr.tracker.domain.model

import com.ajmr.tracker.R

enum class Categories(val label: Int, val transactionType: TransactionType) {
    SALARY(label = R.string.salary, transactionType = TransactionType.INCOME),
    SERVICES(label = R.string.services, transactionType = TransactionType.INCOME),
    INVESTMENTS(label = R.string.investments, transactionType = TransactionType.INCOME),
    GIFTS(label = R.string.gifts, transactionType = TransactionType.INCOME),
    REFUNDS(label = R.string.refunds, transactionType = TransactionType.INCOME),
    OTHER_INCOMES(label = R.string.other_incomes, transactionType = TransactionType.INCOME),
    FOOD(label = R.string.food, transactionType = TransactionType.EXPENSE),
    HOUSE(label = R.string.house, transactionType = TransactionType.EXPENSE),
    TRANSPORT(label = R.string.food, transactionType = TransactionType.EXPENSE),
    SHOPPING(label = R.string.shopping, transactionType = TransactionType.EXPENSE),
    ENTERTAINMENT(label = R.string.entertainment, transactionType = TransactionType.EXPENSE),
    HEALTH(label = R.string.health, transactionType = TransactionType.EXPENSE),
    EDUCATION(label = R.string.education, transactionType = TransactionType.EXPENSE),
    CREDITS(label = R.string.credits, transactionType = TransactionType.EXPENSE),
    OTHER_EXPENSES(label = R.string.other_expenses, transactionType = TransactionType.EXPENSE),
}