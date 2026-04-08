package com.ajmr.tracker.ui.transaction

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.ui.transaction.components.BalanceCard
import com.ajmr.tracker.ui.transaction.components.SummaryContent
import com.ajmr.tracker.ui.transaction.components.SummaryHeader

@Composable
fun TransactionScreenContent(
    incomes: List<Transaction>,
    expenses: List<Transaction>,
    onAddClicked: () -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.clip(ShapeDefaults.ExtraLarge),
            onClick = onAddClicked,
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

            item {
                BalanceCard(
                    totalIncome = incomes.sumOf { it.amount },
                    totalExpense = expenses.sumOf { it.amount }
                )
            }

            item {
                SummaryHeader(TransactionType.INCOME, incomes.size.toString())
            }

            items(incomes) {
                SummaryContent(it)
            }

            item {
                SummaryHeader(TransactionType.EXPENSE, expenses.size.toString())
            }

            items(expenses) {
                SummaryContent(it)
            }
        }
    }
}

@Preview
@Composable
fun TransactionScreenContentPreview() {
    val expenses = listOf(
        Transaction(
            amount = 20.0,
            description = "parrilla",
            category = Categories.FOOD,
            transactionType = TransactionType.EXPENSE,
            date = 123456789
        ),
        Transaction(
            amount = 20.0,
            description = "taxi",
            category = Categories.TRANSPORT,
            transactionType = TransactionType.EXPENSE,
            date = 123456789
        )
    )
    TransactionScreenContent(incomes = expenses, expenses = expenses, onAddClicked = {})
}