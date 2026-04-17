package com.ajmr.tracker.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr.tracker.R

enum class TransactionType(val label: Int, val imageVector: ImageVector) {
    INCOME(label = R.string.income, imageVector = Icons.Default.ArrowUpward),
    EXPENSE(label = R.string.expense, imageVector = Icons.Default.ArrowDownward),
}

@Composable
fun TransactionType.color(): Color {
    return when (this) {
        TransactionType.INCOME -> MaterialTheme.colorScheme.secondary
        TransactionType.EXPENSE -> MaterialTheme.colorScheme.error
    }
}

@Composable
fun TransactionType.backgroundColor(): Color {
    return when (this) {
        TransactionType.INCOME ->
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)

        TransactionType.EXPENSE ->
            MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
    }
}