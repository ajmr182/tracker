package com.ajmr.tracker.ui.expense

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.ui.transaction.TransactionScreenContent
import org.junit.Rule
import org.junit.Test

class ExpenseScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenthereareexpensestheyaredisplayedonthescreen() {

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

        composeTestRule.setContent {
            TransactionScreenContent(
                transactions = expenses,
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