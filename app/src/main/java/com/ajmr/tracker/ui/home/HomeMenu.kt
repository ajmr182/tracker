package com.ajmr.tracker.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Payments
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr.tracker.R
import com.ajmr.tracker.ui.navigation.BalanceRoute
import com.ajmr.tracker.ui.navigation.Route
import com.ajmr.tracker.ui.navigation.TransactionRoute

sealed class HomeMenu(
    val label: Int,
    val icon: ImageVector,
    val route: Route
) {
    data object Income : HomeMenu(R.string.income_menu, Icons.Default.Payments, TransactionRoute)
    data object Balance : HomeMenu(R.string.balance_menu, Icons.Default.BarChart, BalanceRoute)
}

