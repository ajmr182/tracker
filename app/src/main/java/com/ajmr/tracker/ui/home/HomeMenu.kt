package com.ajmr.tracker.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr.tracker.ui.navigation.ExpensesRoute
import com.ajmr.tracker.ui.navigation.IncomeRoute
import com.ajmr.tracker.ui.navigation.Route

sealed class HomeMenu(
    val label: String,
    val icon: ImageVector,
    val route: Route
) {
    data object Income : HomeMenu("Income", Icons.Default.Add, IncomeRoute)
    data object Expenses : HomeMenu("Expenses", Icons.Default.Place, ExpensesRoute)
}

