package com.example.asg2


import com.example.asg2.Data.ExpenseViewModel
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewExpenseActivity : AppCompatActivity() {

    // Variable to interface with DAO
    private lateinit var expenseViewModel: ExpenseViewModel

    private var expenseList = ArrayList<Array<String>>()
    private var adapter: ExpenseAdapter = ExpenseAdapter(this, expenseList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        expenseViewModel.getAllExpenses()?.observe(this) {
            it.let {
                for (expense in it) {
                    expenseList.add(arrayOf(expense.expenseName, expense.amount.toString(), expense.category, expense.date, expense.time, expense.paymentType, expense.notes))
                }
                adapter?.notifyDataSetChanged()
            }
        }

//        Set RecyclerView
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)





        val btnBackToMainMenu: Button = findViewById(R.id.btnBackToMainMenu)
        btnBackToMainMenu.setOnClickListener {
            // Finish the current activity and return to the previous one
            finish()
        }
    }

    private fun loadExpenses() {


    }
}
