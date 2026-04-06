package com.ajmr.tracker.ui.income

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.ui.transaction.TransactionScreenContent
import org.junit.Rule
import org.junit.Test

class IncomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenthereareincomestheyaredisplayedonthescreen() {

        val incomes = listOf(
            Transaction(
                amount = 20.0,
                description = "parrilla",
                category = "comida",
                transactionType = TransactionType.INCOME,
                date = 123456789
            ),
            Transaction(
                amount = 20.0,
                description = "taxi",
                category = "transporte",
                transactionType = TransactionType.INCOME,
                date = 123456789
            )
        )

        composeTestRule.setContent {
            TransactionScreenContent(
                transactions = incomes,
                onAddClicked = {  }
            )
        }

        composeTestRule
            .onNodeWithText("parrilla")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("taxi")
            .assertIsDisplayed()
    }
}