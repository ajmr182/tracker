package com.ajmr.tracker.ui.transaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.R
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.model.color
import com.ajmr.tracker.ui.components.AddButton
import com.ajmr.tracker.ui.theme.TrackerTheme
import com.ajmr.tracker.ui.transaction.components.BalanceCard
import com.ajmr.tracker.ui.transaction.components.SummaryContent
import com.ajmr.tracker.ui.transaction.components.SummaryHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreenContent(
    incomes: List<Transaction>,
    expenses: List<Transaction>,
    onAddClicked: (TransactionType) -> Unit
) {

    LazyColumn {

        item {
            BalanceCard(
                totalIncome = incomes.sumOf { it.amount },
                totalExpense = expenses.sumOf { it.amount }
            )
        }


        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AddButton(TransactionType.INCOME) { transactionType ->
                    onAddClicked(
                        transactionType
                    )
                }
                AddButton(TransactionType.EXPENSE) { transactionType ->
                    onAddClicked(
                        transactionType
                    )
                }
            }
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


@Preview(showBackground = true)
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
    TrackerTheme {
        TransactionScreenContent(incomes = expenses, expenses = expenses, onAddClicked = {})
    }
}