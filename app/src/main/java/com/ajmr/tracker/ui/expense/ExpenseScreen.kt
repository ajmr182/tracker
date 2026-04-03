package com.ajmr.tracker.ui.expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajmr.tracker.data.entity.Transaction

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

        else -> ExpenseScreenContent(
            expenses = state.expenses,
            saveExpense = { viewModel.onEvent(ExpenseEvent.OnSaveExpense(it)) },
        )
    }
}


@Composable
fun ExpenseScreenContent(expenses: List<Transaction>, saveExpense: (Transaction) -> Unit) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.clip(ShapeDefaults.ExtraLarge),
            onClick = {},
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }) { paddingValues ->

        Column(Modifier.padding(paddingValues)) {
            LazyColumn() {
                items(expenses) { expense ->
                    Text(text = expense.description)
                }
            }
        }

    }
}

@Preview
@Composable
fun ExpenseScreenPreview() {
    ExpenseScreenContent(expenses = emptyList()) {}
}