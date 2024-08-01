package com.example.asg2

import androidx.lifecycle.LiveData

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // Function to insert an expense into the database
    suspend fun insert(expenseEntity: Expense_Entity) {
        expenseDao.insert(expenseEntity)
    }

    // Function to update an existing expense
    suspend fun update(expenseEntity: Expense_Entity) {
        expenseDao.update(expenseEntity)
    }

    // Function to delete a specific expense
    suspend fun delete(expenseEntity: Expense_Entity) {
        expenseDao.delete(expenseEntity)
    }

    // Function to get all expenses as LiveData
    fun getAllExpenses(): LiveData<List<Expense_Entity>> {
        return expenseDao.getAllExpenses()
    }
}
