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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.domain.model.TransactionType

@Composable
fun SummaryHeader(
    transactionType: TransactionType,
    totalTransactions: String
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.background(
                color = transactionType.color,
                shape = RoundedCornerShape(4.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = transactionType.imageVector,
                contentDescription = "",
                tint = Color.White
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(text = stringResource(transactionType.label))
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = Color(0xFF1976D2).copy(alpha = 0.1f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(totalTransactions, color = transactionType.color)
        }
    }
}