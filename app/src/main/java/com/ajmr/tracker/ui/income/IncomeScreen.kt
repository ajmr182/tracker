package com.ajmr.tracker.ui.income

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajmr.tracker.ui.components.AddTransactionTypeDialog
import com.ajmr.tracker.ui.transaction.TransactionScreenContent

@Composable
fun IncomeScreen() {

    val viewModel: IncomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setUserId("1")
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is IncomeViewEffect.ShowError -> {

                }
            }
        }
    }

    when {
        state.isLoading -> CircularProgressIndicator()

        !state.error.isNullOrEmpty() -> Text("error")

        else -> TransactionScreenContent(
            transactions = state.incomes,
            onAddClicked = { viewModel.onEvent(IncomeEvent.OnAddClicked) },
        )
    }

    if (state.showAddDialog) {
        AddTransactionTypeDialog(
            onDisMissRequest = {
                viewModel.onEvent(IncomeEvent.OnDismissAddDialog)
            },
            onSaveTransaction = { description, amount, category ->
                viewModel.onEvent(IncomeEvent.OnSaveIncome(description, amount, category))
            }
        )
    }
}