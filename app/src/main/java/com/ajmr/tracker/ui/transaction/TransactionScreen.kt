package com.ajmr.tracker.ui.transaction

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajmr.tracker.ui.components.AddTransactionTypeDialog

@Composable
fun IncomeScreen() {

    val viewModel: TransactionViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setUserId("1")
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is TransactionViewEffect.ShowError -> {

                }
            }
        }
    }

    when {
        state.isLoading -> CircularProgressIndicator()

        !state.error.isNullOrEmpty() -> Text("error")

        else -> TransactionScreenContent(
            incomes = state.incomes,
            expenses = state.expenses,
            onAddClicked = { viewModel.onEvent(TransactionEvent.OnAddClicked) },
        )
    }

    if (state.showAddDialog) {
        AddTransactionTypeDialog(
            onDisMissRequest = {
                viewModel.onEvent(TransactionEvent.OnDismissAddDialog)
            },
            onSaveTransaction = { description, amount, transactionType, category ->
                viewModel.onEvent(TransactionEvent.OnSaveTransaction(description, amount, transactionType, category))
            }
        )
    }
}