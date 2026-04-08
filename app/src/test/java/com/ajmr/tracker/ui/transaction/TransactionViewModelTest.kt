package com.ajmr.tracker.ui.transaction

import app.cash.turbine.test
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.repository.TransactionRepository
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
class TransactionViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var fakeRepo: FakeTransactionRepository
    private lateinit var viewModel: TransactionViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        fakeRepo = FakeTransactionRepository()
        viewModel = TransactionViewModel(fakeRepo)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When repo emits data, uiState is updated`() = runTest {
        val transaction = Transaction(
            amount = 20.0,
            description = "parrilla",
            category = Categories.FOOD,
            transactionType = TransactionType.INCOME,
            date = 123456789
        )

        viewModel.setUserId("123")
        viewModel.uiState.test {
            val initial = awaitItem()
            assert(!initial.isLoading)
            assert(initial.incomes.isEmpty())

            fakeRepo.emitExpenses(listOf(transaction))

            val updated = awaitItem()
            assert(updated.incomes == listOf(transaction))
            assert(!updated.isLoading)
        }
    }

    @Test
    fun `When OnSaveExpense is triggered, the repository is called`() = runTest {
        val repository = mockk<TransactionRepository>(relaxed = true)
        val viewModel = TransactionViewModel(repository)

        val transaction = Transaction(
            amount = 20.0,
            description = "parrilla",
            category = Categories.FOOD,
            transactionType = TransactionType.INCOME,
            date = 123456789
        )

        viewModel.onEvent(TransactionEvent.OnSaveTransaction(transaction.description, transaction.amount, transaction.category))

        advanceUntilIdle()

        coVerify {
            repository.insertTransaction(match {
                it.amount == transaction.amount && it.description == transaction.description && it.category == transaction.category && it.transactionType == transaction.transactionType
            })
        }
    }
}