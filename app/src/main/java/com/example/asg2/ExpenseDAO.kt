package com.example.asg2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseDao {

    // Define database operations

    @Insert
    suspend fun insert(expenseEntity: Expense_Entity)

    @Update
    suspend fun update(expenseEntity: Expense_Entity)

    @Delete
    suspend fun delete(expenseEntity: Expense_Entity)

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): LiveData<List<Expense_Entity>>
}
