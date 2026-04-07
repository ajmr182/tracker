package com.ajmr.tracker.data.repository

import app.cash.turbine.test
import com.ajmr.tracker.data.dao.ExpenseDao
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TransactionRepositoryImplTest {

    @Test
    fun `getExpenses returns data from the DAO`() = runTest {
        val list = listOf(
            Transaction(
                amount = 20.0,
                description = "parrilla",
                category = Categories.FOOD,
                transactionType = TransactionType.EXPENSE,
                date = 123456789
            )
        )

        val flow = flowOf(list)

        val dao = mockk<ExpenseDao>()
        every { dao.getTransactions() } returns flow

        val repo = TransactionRepositoryImpl(dao)

        val result = repo.getTransactions().first()

        assert(result == list)
    }

    @Test
    fun `getExpenses correctly outputs data`() = runTest {
        val list = listOf(
            Transaction(
                amount = 20.0,
                description = "parrilla",
                category = Categories.FOOD,
                transactionType = TransactionType.EXPENSE,
                date = 123456789
            )
        )

        val dao = mockk<ExpenseDao>()
        every { dao.getTransactions() } returns flowOf(list)

        val repository = TransactionRepositoryImpl(dao)

        repository.getTransactions().test {
            val item = awaitItem()
            assert(item == list)
            awaitComplete()
        }
    }
}