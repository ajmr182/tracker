package com.ajmr.tracker.ui.expense

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ajmr.tracker.data.entity.Expense
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class ExpenseScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenthereareexpensestheyaredisplayedonthescreen() {

        val expenses = listOf(
            Expense(
                amount = 20.0,
                description = "parrilla",
                category = "comida",
                date = 123456789
            ),
            Expense(
                amount = 20.0,
                description = "taxi",
                category = "transporte",
                date = 123456789
            )
        )

        composeTestRule.setContent {
            ExpenseScreenContent(
                expenses = expenses,
                saveExpense = {}
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