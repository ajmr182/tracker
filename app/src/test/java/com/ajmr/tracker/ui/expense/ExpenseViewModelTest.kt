package com.ajmr.tracker.ui.expense

import app.cash.turbine.test
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.repository.ExpenseRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExpenseViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var fakeRepo: FakeExpenseRepository
    private lateinit var viewModel: ExpenseViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        fakeRepo = FakeExpenseRepository()
        viewModel = ExpenseViewModel(fakeRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When repo emits data, uiState is updated`() = runTest {

        val list = listOf(
            Transaction(
                amount = 20.0,
                description = "parrilla",
                category = "comida",
                transactionType = TransactionType.EXPENSE,
                date = 123456789
            )
        )

        viewModel.uiState.test {

            viewModel.setUserId("123")

            skipItems(1)

            val loading = awaitItem()
            assert(loading.isLoading)

            fakeRepo.emitExpenses(list)
            advanceUntilIdle()

            val updated = awaitItem()
            assert(updated.expenses == list)
        }
    }

    @Test
    fun `When OnSaveExpense is triggered, the repository is called`() = runTest {

        val repository = mockk<ExpenseRepository>(relaxed = true)
        val viewModel = ExpenseViewModel(repository)

        val transaction = Transaction(
            amount = 20.0,
            description = "parrilla",
            category = "comida",
            transactionType = TransactionType.EXPENSE,
            date = 123456789
        )

        viewModel.onEvent(ExpenseEvent.OnSaveExpense(transaction))
        advanceUntilIdle()

        coVerify {
            repository.insertTransaction(transaction)
        }
    }
}

