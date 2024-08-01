package com.example.asg2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ViewModel(application: Application): AndroidViewModel(application) {
    // DB data
    private var repository: ExpenseRepository
    private var expense: LiveData<List<Expense>>


    init {
        repository = ExpenseRepository(application)
        expense = repository.expenses

    }

    // Report API
    fun addExpense(expense: Expense) {
        repository.insert(expense)
    }

    fun updateRecord(expense: Expense) {
        repository.update(expense)
    }

    fun deleteRecord(expense: Expense) {
        repository.delete(expense)
    }

    fun getExpense(expenseID: Int): LiveData<List<Expense>> {
        return repository.getExpense(expenseID)
    }

    fun getAllExpense(): LiveData<List<Expense>> {
        return expense
    }
}