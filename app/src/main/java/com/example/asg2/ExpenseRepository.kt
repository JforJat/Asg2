package com.example.asg2

import androidx.lifecycle.LiveData

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // Function to insert an expense into the database
    suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    // Function to update an existing expense
    suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }

    // Function to delete a specific expense
    suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    // Function to get all expenses as LiveData
    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
    }
}
