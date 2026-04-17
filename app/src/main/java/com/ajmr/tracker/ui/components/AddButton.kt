package com.ajmr.tracker.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.model.color

@Composable
fun AddButton(transactionType: TransactionType, onAddClicked: (TransactionType) -> Unit) {
    Button(
        colors = ButtonColors(
            contentColor = Color.White,
            containerColor = transactionType.color(),
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ), onClick = { onAddClicked(transactionType) }) {
        Text(text = stringResource(transactionType.label))
    }
}

@Preview
@Composable
fun AddButtonPreview() {
    AddButton(TransactionType.INCOME) { }
}