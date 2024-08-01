package com.example.asg2

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ExpenseDao {
    @Insert
    fun addExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expense WHERE id=:id")
    fun getExpense(id: Int): LiveData<List<Expense>>

    @Query("SELECT COUNT(*) FROM expense")
    fun getExpenseCount(): Int

    @Query("SELECT * FROM expense")
    fun getAllExpense(): LiveData<List<Expense>>


}