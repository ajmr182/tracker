package com.ajmr.tracker.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajmr.tracker.data.entity.Transaction
import com.ajmr.tracker.domain.model.Categories
import com.ajmr.tracker.domain.model.TransactionType
import com.ajmr.tracker.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val userId = MutableStateFlow<String?>(null)

    private val _uiState = MutableStateFlow(TransactionUiState())
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<TransactionUiState> = userId
        .filterNotNull()
        .flatMapLatest {
            transactionRepository.getTransactions()
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Error cargando gastos"
                        )
                    }
                    emit(emptyList())
                }
        }
        .combine(_uiState) { transactions, currentState ->
            currentState.copy(
                incomes = transactions.filter { it.transactionType == TransactionType.INCOME },
                expenses = transactions.filter { it.transactionType == TransactionType.EXPENSE },
                isLoading = false
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TransactionUiState()
        )


    private val _events = MutableSharedFlow<TransactionViewEffect>()
    val events: SharedFlow<TransactionViewEffect> = _events

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.OnSaveTransaction -> saveExpense(
                event.description,
                event.amount,
                event.category
            )

            TransactionEvent.OnAddClicked -> _uiState.update { it.copy(showAddDialog = true) }
            TransactionEvent.OnDismissAddDialog -> _uiState.update { it.copy(showAddDialog = false) }
        }
    }

    fun setUserId(id: String) {
        userId.value = id
    }

    private fun saveExpense(
        description: String,
        amount: Double,
        category: Categories,
    ) = viewModelScope.launch {
            try {
                val transaction = Transaction(
                    amount = amount,
                    category = category,
                    description = description,
                    transactionType = TransactionType.INCOME
                )
                transactionRepository.insertTransaction(transaction)
            } catch (_: Exception) {
                _events.emit(TransactionViewEffect.ShowError("error de guardado"))
            }
        }
}