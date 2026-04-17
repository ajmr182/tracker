package com.ajmr.tracker.ui.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.model.backgroundColor
import com.ajmr.tracker.domain.model.color
import com.ajmr.tracker.ui.theme.TrackerTheme

@Composable
fun SummaryHeader(
    transactionType: TransactionType,
    totalTransactions: String
) {

    val typeColor = transactionType.color()
    val typeBackground = transactionType.backgroundColor()

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = typeBackground,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = transactionType.imageVector,
                contentDescription = "",
                tint = typeColor
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(transactionType.label),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(
                    color = typeBackground,
                    shape = CircleShape
                )
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = totalTransactions,
                color = typeColor,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryHeaderPreview() {
    TrackerTheme() {
        SummaryHeader(TransactionType.INCOME, "10")
    }
}