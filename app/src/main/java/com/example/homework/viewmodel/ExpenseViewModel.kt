package com.example.homework.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.homework.model.Expense

class ExpenseViewModel : ViewModel() {

    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    var name by mutableStateOf("")
        private set
    var amount by mutableStateOf("")
        private set
    var error by mutableStateOf<String?>(null)
        private set

    // Total dihitung dari list, jadi otomatis berubah saat tambah atau hapus
    val total: Long get() = _expenses.sumOf { it.amount }

    private var nextId = 0

    fun onNameChange(value: String) {
        name = value
        error = null
    }

    fun onAmountChange(value: String) {
        // Hanya angka yang boleh masuk
        amount = value.filter { it.isDigit() }
        error = null
    }

    fun addExpense() {
        val value = amount.toLongOrNull() ?: 0L

        if (name.isBlank()) {
            error = "Expense name must not be empty"
            return
        }
        if (value <= 0L) {
            error = "Amount must be greater than 0"
            return
        }

        _expenses.add(Expense(nextId++, name.trim(), value))
        name = ""
        amount = ""
        error = null
    }

    fun deleteExpense(expense: Expense) {
        _expenses.remove(expense)
    }
}