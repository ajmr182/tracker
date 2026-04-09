package com.ajmr.tracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajmr.tracker.R
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionTypeDialog(
    onDisMissRequest: () -> Unit,
    onSaveTransaction: (description: String, amount: Double, transactionType: TransactionType, category: Categories) -> Unit,
) {

    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    val transactionTypes = TransactionType.entries
    var transactionTypeSelected by remember { mutableStateOf(transactionTypes.first()) }

    val categories by remember(transactionTypeSelected) {
        derivedStateOf {
            Categories.entries.filter { it.transactionType == transactionTypeSelected }
        }
    }

    var selectedCategory by remember(transactionTypeSelected) {
        mutableStateOf(categories.firstOrNull() ?: Categories.entries.first())
    }

    var expandedTransaction by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    BasicAlertDialog(
        onDismissRequest = onDisMissRequest,
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = stringResource(R.string.add_transaction))

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(R.string.description)) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = amount,
                    onValueChange = { input ->
                        if (input.matches(Regex("^\\d*\\.?\\d*$"))) {
                            amount = input
                        }
                    },
                    label = { Text(stringResource(R.string.amount)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedTransaction,
                    onExpandedChange = { expandedTransaction = !expandedTransaction }
                ) {
                    OutlinedTextField(
                        value = stringResource(transactionTypeSelected.label),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.transaction_type)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expandedTransaction)
                        },
                        modifier = Modifier
                            .menuAnchor(
                                type = MenuAnchorType.PrimaryEditable,
                                enabled = true
                            )
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTransaction,
                        onDismissRequest = { expandedTransaction = false }
                    ) {
                        transactionTypes.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(stringResource(item.label)) },
                                onClick = {
                                    transactionTypeSelected = item
                                    expandedTransaction = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedCategory,
                    onExpandedChange = { expandedCategory = !expandedCategory }
                ) {

                    OutlinedTextField(
                        value = stringResource(selectedCategory.label),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.categories)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expandedCategory)
                        },
                        modifier = Modifier
                            .menuAnchor(
                                type = MenuAnchorType.PrimaryEditable,
                                enabled = true
                            )
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedCategory,
                        onDismissRequest = { expandedCategory = false }
                    ) {
                        categories.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(stringResource(item.label)) },
                                onClick = {
                                    selectedCategory = item
                                    expandedCategory = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (description.isEmpty() || amount.isEmpty()) return@Button
                        onSaveTransaction(
                            description,
                            amount.toDouble(),
                            transactionTypeSelected,
                            selectedCategory
                        )
                        onDisMissRequest()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    }
}

@Preview
@Composable
fun AddTransactionTypeDialogPreview() {
    AddTransactionTypeDialog(
        onDisMissRequest = {},
        onSaveTransaction = { description, amount, transactionType, category -> },
    )
}