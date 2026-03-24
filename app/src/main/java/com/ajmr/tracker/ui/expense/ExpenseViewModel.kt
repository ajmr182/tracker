package com.ajmr.tracker.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajmr.tracker.data.entity.Expense
import com.ajmr.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val userId = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ExpenseUiState> = userId
        .filterNotNull()
        .flatMapLatest {
            expenseRepository.getExpenses()
                .map { expenses ->
                    ExpenseUiState(expenses = expenses)
                }
                .onStart {
                    emit(ExpenseUiState(isLoading = true))
                }
                .catch {
                    emit(ExpenseUiState(error = "Error cargando gastos"))
                }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ExpenseUiState()
        )

    private val _events = MutableSharedFlow<ExpenseViewEffect>()
    val events: SharedFlow<ExpenseViewEffect> = _events

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.OnSaveExpense -> saveExpense(event.expense)
        }
    }

    fun setUserId(id: String) {
        userId.value = id
    }

    private fun saveExpense(expense: Expense) = viewModelScope.launch {
        try {
            expenseRepository.addExpense(expense)
        } catch (e: Exception) {
            _events.emit(ExpenseViewEffect.ShowError("error de guardado"))
        }
    }
}