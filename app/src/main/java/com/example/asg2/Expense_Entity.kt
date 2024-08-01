package com.example.asg2

import androidx.room.Entity
import androidx.room.PrimaryKey

// Expense entity
@Entity(tableName = "expenses")
data class Expense_Entity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expenseName: String,
    val amount: String,
    val category: String,
    val date: String,
    val time: String,
    val paymentType: String,
    val notes: String,
)