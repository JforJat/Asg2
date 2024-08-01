package com.example.asg2

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseRepository(application: Application) {
    private var expenseDao: ExpenseDao
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var expenses: LiveData<List<Expense>>

    init {
        val db = ExpenseDatabase.getDatabase(application)
        expenseDao = db!!.getExpenseDao()
        expenses = expenseDao.getAllExpense()
    }

    // Function to insert an expense into the database
    fun insert(expenseEntity: Expense) {
        coroutineScope.launch (Dispatchers.IO) {
            expenseDao.addExpense(expenseEntity)
        }
    }

    // Function to update an existing expense
     fun update(expenseEntity: Expense) {
        coroutineScope.launch (Dispatchers.IO) {
            expenseDao.updateExpense(expenseEntity)
        }
    }

    // Function to delete a specific expense
     fun delete(expenseEntity: Expense) {
            expenseDao.deleteExpense(expenseEntity)
    }

    fun getExpense(id: Int): LiveData<List<Expense>> {
            return expenseDao.getExpense(id)
    }

    // Function to get all expenses as LiveData
    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpense()
    }
}
