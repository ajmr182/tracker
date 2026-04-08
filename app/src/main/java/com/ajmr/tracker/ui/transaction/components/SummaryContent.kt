package com.ajmr.tracker.ui.transaction.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.ui.theme.AppGray
import com.ajmr.tracker.ui.theme.AppWhite

@Composable
fun SummaryContent(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        border = BorderStroke(1.dp, color = AppGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = AppWhite)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = transaction.transactionType.color.copy(alpha = 0.1f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = transaction.category.imageVector,
                    contentDescription = "",
                    tint = transaction.transactionType.color
                )
            }

            Column {
                Text(
                    text = stringResource(transaction.category.label) + " - " + transaction.description,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = transaction.date.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(end = 16.dp),
                color = transaction.transactionType.color,
                text = "S/" + transaction.amount.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun SummaryContentPreview() {
    val expenses = Transaction(
            amount = 20.0,
            description = "parrilla",
            category = Categories.FOOD,
            transactionType = TransactionType.EXPENSE,
            date = 123456789
        )

    SummaryContent(expenses)
}