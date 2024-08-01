package com.example.asg2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class ViewExpenseActivity : AppCompatActivity() {

    // Variable to interface with DAO


    //private lateinit var expenses: List<String>
    //private lateinit var expenseID: List<Int>

    private var expenseList = ArrayList<Array<String>>()
//    private var adapter: ExpenseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)

        // Get all expenses data from the view model
        //viewModel.getAllExpense().observe(this) {
          //  expenses = it.map { Expense ->
            //    Expense.expenseName
            //}
            //expenseID = it.map { Expense ->
             //   Expense.id
           // }
        //}

        //adapter = ExpenseAdapter(this, expenseList)
        val recyclerExpense = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerExpense.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerExpense.adapter = ExpenseAdapter(this, expenseList)

        // Initialize the ViewModel


        //loadExpenses()

        val btnBackToMainMenu: Button = findViewById(R.id.btnBackToMainMenu)
        btnBackToMainMenu.setOnClickListener {
            // Finish the current activity and return to the previous one
            finish()
        }
    }

    private fun loadExpenses() {
        // Load expenses from the database or any other data source
        // For example, you can use a repository to fetch expenses

        //expenseList.clear()
        //expenseList.addAll(expenses)


    }
}
