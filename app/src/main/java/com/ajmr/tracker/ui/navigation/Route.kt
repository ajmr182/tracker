package com.ajmr.tracker.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey

@Serializable
data object TransactionRoute : Route

@Serializable
data object BalanceRoute : Route