package com.ajmr.tracker.data.repository

import app.cash.turbine.test
import com.ajmr.tracker.data.dao.ExpenseDao
import com.ajmr.tracker.data.entity.Expense
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ExpenseRepositoryImplTest {

    @Test
    fun `getExpenses returns data from the DAO`() = runTest {
        val list = listOf(
            Expense(
                amount = 20.0,
                description = "parrilla",
                category = "comida",
                date = 123456789
            )
        )

        val flow = flowOf(list)

        val dao = mockk<ExpenseDao>()
        every { dao.getExpenses() } returns flow

        val repo = ExpenseRepositoryImpl(dao)

        val result = repo.getExpenses().first()

        assert(result == list)
    }

    @Test
    fun `getExpenses correctly outputs data`() = runTest {
        val list = listOf(
            Expense(
                amount = 20.0,
                description = "parrilla",
                category = "comida",
                date = 123456789
            )
        )

        val dao = mockk<ExpenseDao>()
        every { dao.getExpenses() } returns flowOf(list)

        val repository = ExpenseRepositoryImpl(dao)

        repository.getExpenses().test {
            val item = awaitItem()
            assert(item == list)
            awaitComplete()
        }
    }
}