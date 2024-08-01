package com.example.asg2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewExpenseActivity(application: Application) : AndroidViewModel(application) {
    private val repository: ExpenseRepository
    val allExpenses: LiveData<List<Expense_Entity>>


    init {
        val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        allExpenses = repository.getAllExpenses()
    }

    // Function to insert an expense
    fun insert(expenseEntity: Expense_Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(expenseEntity)
    }

    // Function to update an existing expense
    fun update(expenseEntity: Expense_Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(expenseEntity)
    }

    // Function to delete a specific expense
    fun delete(expenseEntity: Expense_Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(expenseEntity)
    }
}
