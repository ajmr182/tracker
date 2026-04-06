package com.ajmr.tracker.ui.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.ajmr.tracker.data.entity.Transaction

@Composable
fun TransactionScreenContent(
    transactions: List<Transaction>,
    onAddClicked: () -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.clip(ShapeDefaults.ExtraLarge),
            onClick =  onAddClicked,
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }) { paddingValues ->

        Column(Modifier.padding(paddingValues)) {
            LazyColumn() {
                items(transactions) { transaction ->
                    Text(text = transaction.description)
                }
            }
        }
    }
}