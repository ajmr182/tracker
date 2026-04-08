package com.ajmr.tracker.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CreditScore
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr.tracker.R

enum class Categories(val label: Int, val transactionType: TransactionType, val imageVector: ImageVector) {
    SALARY(label = R.string.salary, transactionType = TransactionType.INCOME, Icons.Default.Money),
    SERVICES(label = R.string.services, transactionType = TransactionType.INCOME, Icons.Default.MiscellaneousServices),
    INVESTMENTS(label = R.string.investments, transactionType = TransactionType.INCOME, Icons.Default.MonetizationOn),
    GIFTS(label = R.string.gifts, transactionType = TransactionType.INCOME, Icons.Default.CardGiftcard),
    REFUNDS(label = R.string.refunds, transactionType = TransactionType.INCOME, Icons.Default.AttachMoney),
    OTHER_INCOMES(label = R.string.other_incomes, transactionType = TransactionType.INCOME, Icons.Default.EmojiEmotions),
    FOOD(label = R.string.food, transactionType = TransactionType.EXPENSE, Icons.Default.Fastfood),
    HOUSE(label = R.string.house, transactionType = TransactionType.EXPENSE, Icons.Default.House),
    TRANSPORT(label = R.string.food, transactionType = TransactionType.EXPENSE, Icons.Default.DirectionsCar),
    SHOPPING(label = R.string.shopping, transactionType = TransactionType.EXPENSE, Icons.Default.Shop),
    ENTERTAINMENT(label = R.string.entertainment, transactionType = TransactionType.EXPENSE, Icons.Default.Tv),
    HEALTH(label = R.string.health, transactionType = TransactionType.EXPENSE, Icons.Default.HealthAndSafety),
    EDUCATION(label = R.string.education, transactionType = TransactionType.EXPENSE, Icons.Default.School),
    CREDITS(label = R.string.credits, transactionType = TransactionType.EXPENSE, Icons.Default.CreditCard),
    OTHER_EXPENSES(label = R.string.other_expenses, transactionType = TransactionType.EXPENSE, Icons.Default.CreditScore),
}