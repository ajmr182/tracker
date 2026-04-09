package com.ajmr.tracker.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ajmr.tracker.ui.balance.BalanceScreen
import com.ajmr.tracker.ui.transaction.IncomeScreen

@Composable
fun NavDisplay(backStack: NavBackStack<NavKey>, paddingValues: PaddingValues) {

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(paddingValues),
        entryProvider = entryProvider {
            entry<TransactionRoute> {
                IncomeScreen()
            }
            entry<BalanceRoute> {
                BalanceScreen()
            }
        }
    )
}