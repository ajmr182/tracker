package com.ajmr.tracker.ui.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountBalanceWallet
import androidx.compose.material.icons.twotone.ArrowDownward
import androidx.compose.material.icons.twotone.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.R
import com.ajmr.tracker.ui.theme.AppGreen
import com.ajmr.tracker.ui.theme.AppRed

@Composable
fun BalanceCard(totalIncome: Double, totalExpense: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color(0xFF1976D2).copy(alpha = 0.1f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.TwoTone.AccountBalanceWallet,
                    contentDescription = null,
                    tint = Color(0xFF1976D2)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.total_balance),
                style = MaterialTheme.typography.bodyMedium
            )


            Text(
                text = "S/ ${totalIncome - totalExpense}",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.TwoTone.ArrowUpward,
                            contentDescription = null,
                            tint = AppGreen
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(stringResource(R.string.income))
                    }

                    Text(
                        text = "+S/ $totalIncome",
                        color = AppGreen,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(horizontal = 8.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.TwoTone.ArrowDownward,
                            contentDescription = null,
                            tint = AppRed
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(stringResource(R.string.expense))
                    }

                    Text(
                        text = "-S/ $totalExpense",
                        color = AppRed,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}