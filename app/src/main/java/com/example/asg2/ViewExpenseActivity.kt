package com.example.asg2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ViewExpenseActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel
    private lateinit var expenses: List<String>
    private lateinit var expenseID: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)

        // Initialize the ViewModel
       // viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        // Get all expenses data from the view model
        //viewModel.getAllExpense().observe(this) {
          //  expenses = it.map { Expense ->
            //    Expense.expenseName
            //}
            //expenseID = it.map { Expense ->
             //   Expense.id
           // }
        //}

        val btnBackToMainMenu: Button = findViewById(R.id.btnBackToMainMenu)
        btnBackToMainMenu.setOnClickListener {
            // Finish the current activity and return to the previous one
            finish()
        }
    }
}
