package com.example.asg2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var viewExpenseActivity: ViewExpenseActivity


    @SuppressLint("SetTextI18n", "DefaultLocale", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_add_expense)
        //viewExpenseActivity = ViewModelProvider(this)[ViewExpenseActivity::class.java]


        // Setup Category Spinner
        val spinnerCategory: Spinner = findViewById(R.id.spCategory)
        val categories = arrayOf(
            "Select",
            "Food and Drinks",
            "Entertainment",
            "Shopping",
            "Travel",
            "School",
            "Necessity",
            "Rent"
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = categoryAdapter

        // Setup Payment Spinner
        val spinnerPayment: Spinner = findViewById(R.id.spPayment)
        val paymentTypes = arrayOf(
            "Select", "Cash", "E-Wallet", "Debit Card", "Credit Card"
        )
        val paymentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentTypes)
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPayment.adapter = paymentAdapter

        // Setup Date Picker
        val etDate: EditText = findViewById(R.id.etDate)
        etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this, { _, selectedYear, selectedMonth, selectedDay ->
                    etDate.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                }, year, month, day
            )
            datePickerDialog.show()
        }

        // Setup Time Picker
        val etTime: EditText = findViewById(R.id.etTime)
        etTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                this, { _, selectedHour, selectedMinute ->
                    val amPm = if (selectedHour >= 12) "PM" else "AM"
                    val hourIn12Format = if (selectedHour % 12 == 0) 12 else selectedHour % 12
                    etTime.setText(
                        String.format(
                            "%02d:%02d %s", hourIn12Format, selectedMinute, amPm
                        )
                    )
                }, hour, minute, false
            )
            timePickerDialog.show()
        }

        // Setup Submit Button
        val btnSubmit: Button = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val expenseName = findViewById<EditText>(R.id.etExpenseName).text.toString()
            val amount = findViewById<EditText>(R.id.etAmount).text.toString()
            val selectedCategory = spinnerCategory.selectedItem.toString()
            val dateText = etDate.text.toString()
            val timeText = etTime.text.toString()
            val selectedPayment = spinnerPayment.selectedItem.toString()

            // Validate inputs
            when {
                expenseName.isEmpty() -> showToast("Please enter an expense name")
                amount.isEmpty() -> showToast("Please enter an amount")
                selectedCategory == "Select" -> showToast("Please select a category")
                dateText.isEmpty() -> showToast("Please select a date")
                timeText.isEmpty() -> showToast("Please select a time")
                selectedPayment == "Select" -> showToast("Please select a payment type")
                !dateText.matches(Regex("\\d{2}/\\d{2}/\\d{4}")) -> showToast("Invalid date format")
                else -> {
                    val expense = Expense(
                        expenseName = expenseName,
                        amount = amount,
                        category = selectedCategory,
                        date = dateText,
                        time = timeText,
                        paymentType = selectedPayment
                    )
                    // Insert the expense into the database
                    viewExpenseActivity.insert(expense)
                    showToast("Expense added")
                    clearInputs()
                }
            }
        }

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ExpenseAdapter { expense -> showEditDialog(expense) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load data
        viewExpenseActivity.allExpenses.observe(this) { expenses ->
            expenses?.let { adapter.submitList(it) }
        }

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Helper functions
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Clear inputs
    @SuppressLint("SetTextI18n")
    private fun clearInputs() {
        findViewById<EditText>(R.id.etExpenseName).text.clear()
        findViewById<EditText>(R.id.etAmount).text.clear()
        findViewById<EditText>(R.id.etDate).text.clear()
        findViewById<EditText>(R.id.etTime).text.clear()
        findViewById<Spinner>(R.id.spCategory).setSelection(0)
        findViewById<Spinner>(R.id.spPayment).setSelection(0)
    }

    // Edit dialog
    private fun showEditDialog(expense: Expense) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Expense")

        // Inflate the dialog layout
        val view = layoutInflater.inflate(R.layout.activity_edit_expense, null)
        builder.setView(view)

        val etExpenseName: EditText = view.findViewById(R.id.etExpenseName)
        val etAmount: EditText = view.findViewById(R.id.etAmount)
        val etDate: EditText = view.findViewById(R.id.etDate)
        val etTime: EditText = view.findViewById(R.id.etTime)
        val spinnerCategory: Spinner = view.findViewById(R.id.spCategory)
        val spinnerPayment: Spinner = view.findViewById(R.id.spPayment)

        // Populate fields with existing data
        etExpenseName.setText(expense.expenseName)
        etAmount.setText(expense.amount)
        etDate.setText(expense.date)
        etTime.setText(expense.time)

        // Set up spinners with existing data
        val categoryAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.categories)
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = categoryAdapter
        spinnerCategory.setSelection(categoryAdapter.getPosition(expense.category))

        val paymentAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.payment_types)
        )
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPayment.adapter = paymentAdapter
        spinnerPayment.setSelection(paymentAdapter.getPosition(expense.paymentType))

        // Set up buttons
        builder.setPositiveButton("Save") { _, _ ->
            val updatedExpense = expense.copy(
                expenseName = etExpenseName.text.toString(),
                amount = etAmount.text.toString(),
                date = etDate.text.toString(),
                time = etTime.text.toString(),
                category = spinnerCategory.selectedItem.toString(),
                paymentType = spinnerPayment.selectedItem.toString()
            )
            // Update the expense in the database
            viewExpenseActivity.update(updatedExpense)
            showToast("Expense updated")
        }

        // Cancel or delete
        builder.setNegativeButton("Cancel", null)
        builder.setNeutralButton("Delete") { _, _ ->
            viewExpenseActivity.delete(expense)
            showToast("Expense deleted")
        }
        // Show the dialog
        builder.show()
    }

    // Back to Main Menu
    fun goToMainMenu(view: android.view.View) {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

    fun showEditDialog(view: View) {
        val expense = view.tag as Expense
        showEditDialog(expense)
    }
}
