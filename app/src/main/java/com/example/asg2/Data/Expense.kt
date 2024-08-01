package com.example.asg2.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Expense entity
@Entity(tableName = "expense")
class Expense(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int = 0,

    @ColumnInfo(name="name")
    val expenseName: String,

    @ColumnInfo(name="amount")
    val amount: Double,

    @ColumnInfo(name="category")
    val category: String,

    @ColumnInfo(name="date")
    val date: String,

    @ColumnInfo(name="time")
    val time: String,

    @ColumnInfo(name="payment_type")
    val paymentType: String,

    @ColumnInfo(name="notes")
    val notes: String,
)