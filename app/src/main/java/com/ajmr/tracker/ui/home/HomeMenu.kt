package com.ajmr.tracker.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr.tracker.R
import com.ajmr.tracker.ui.navigation.Route
import com.ajmr.tracker.ui.navigation.TransactionRoute

sealed class HomeMenu(
    val label: Int,
    val icon: ImageVector,
    val route: Route
) {
    data object Income : HomeMenu(R.string.transactions, Icons.Default.Add, TransactionRoute)
}

