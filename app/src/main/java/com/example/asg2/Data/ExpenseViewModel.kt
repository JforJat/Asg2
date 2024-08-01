package com.example.asg2.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: ExpenseRepository
    private var allExpense: LiveData<List<Expense>>?

    init {
        mRepository = ExpenseRepository(application)
        allExpense = mRepository.expenses
    }

    fun getAllExpense(): LiveData<List<Expense>>? {
        return allExpense
    }

    fun insert(expense: Expense) {
        mRepository.insert(expense)
    }

    fun update(expense: Expense) {
        mRepository.update(expense)
    }

    fun delete(expense: Expense) {
        mRepository.delete(expense)
    }

    fun getExpense(id: Int): LiveData<List<Expense>>? {
        return mRepository.getExpense(id)
    }

    fun getAllExpenses(): LiveData<List<Expense>>? {
        return mRepository.getAllExpenses()
    }

}