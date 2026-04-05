package com.ajmr.tracker.ui.expense

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.ui.components.AddTransactionTypeDialog
import com.ajmr.tracker.ui.transaction.TransactionScreenContent

@Composable
fun ExpenseScreen() {

    val viewModel: ExpenseViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setUserId("1")
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is ExpenseViewEffect.ShowError -> {

                }
            }
        }
    }

    when {
        state.isLoading -> CircularProgressIndicator()

        !state.error.isNullOrEmpty() -> Text("error")

        else -> TransactionScreenContent(
            transactions = state.expenses,
            onAddClicked = { viewModel.onEvent(ExpenseEvent.OnAddClicked) },
        )
    }

    if (state.showAddDialog) {
        AddTransactionTypeDialog(
            onDisMissRequest = {
                viewModel.onEvent(ExpenseEvent.OnDismissAddDialog)
            },
            onSaveTransaction = { description, amount, category ->
                viewModel.onEvent(ExpenseEvent.OnSaveExpense(description, amount, category))
            }
        )
    }
}