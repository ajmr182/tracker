package com.ajmr.tracker.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr.tracker.R
import com.ajmr.tracker.ui.theme.AppGreen
import com.ajmr.tracker.ui.theme.AppRed

enum class TransactionType(val label: Int, val imageVector: ImageVector, val color: Color) {
    INCOME(label = R.string.income, imageVector = Icons.Default.ArrowUpward, AppGreen),
    EXPENSE(label = R.string.expense, imageVector = Icons.Default.ArrowDownward, AppRed),
}