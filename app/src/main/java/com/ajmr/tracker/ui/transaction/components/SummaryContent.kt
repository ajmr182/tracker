package com.ajmr.tracker.ui.transaction.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.R
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.model.backgroundColor
import com.ajmr.tracker.domain.model.color
import com.ajmr.tracker.ui.formatAmountPe
import com.ajmr.tracker.ui.formatTransactionDate
import com.ajmr.tracker.ui.theme.TrackerTheme

@Composable
fun SummaryContent(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = transaction.transactionType.backgroundColor(),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = transaction.category.imageVector,
                    contentDescription = "",
                    tint = transaction.transactionType.color()
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(
                        R.string.transaction_label,
                        stringResource(transaction.category.label),
                        transaction.description
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = formatTransactionDate(transaction.date),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .widthIn(max = 120.dp),
                color = transaction.transactionType.color(),
                text = formatAmountPe(transaction.amount),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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

    TrackerTheme() {
        SummaryContent(expenses)
    }
}