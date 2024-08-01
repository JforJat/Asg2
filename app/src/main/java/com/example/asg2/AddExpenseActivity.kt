package com.example.asg2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var viewExpenseActivity: ViewExpenseActivity


    @SuppressLint("SetTextI18n", "DefaultLocale", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

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

        // Setup Payment Spinner
        val spinnerPayment: Spinner = findViewById(R.id.spPayment)
        val paymentTypes = arrayOf(
            "Select", "Cash", "E-Wallet", "Debit Card", "Credit Card"
        )
        val paymentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentTypes)
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPayment.adapter = paymentAdapter

        val btnBackToMainMenu: Button = findViewById(R.id.btnBackToMainMenu)
        btnBackToMainMenu.setOnClickListener {
            finish()
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
            val notes = findViewById<EditText>(R.id.etNotes).text.toString()

            // Validate inputs
            when {
                expenseName.isEmpty() -> showToast("Please enter an expense name")
                amount.isEmpty() -> showToast("Please enter an amount")
                selectedCategory == "Select" -> showToast("Please select a category")
                dateText.isEmpty() -> showToast("Please select a date")
                timeText.isEmpty() -> showToast("Please select a time")
                selectedPayment == "Select" -> showToast("Please select a payment type")

                else -> {
                    val expenseEntity = Expense_Entity(
                        expenseName = expenseName,
                        amount = amount,
                        category = selectedCategory,
                        date = dateText,
                        time = timeText,
                        paymentType = selectedPayment,
                        notes = notes
                    )
                    // Show confirmation dialog
                    showConfirmationDialog(expenseEntity)
                }
            }
        }
    }

    // Show confirmation dialog
    private fun showConfirmationDialog(expenseEntity: Expense_Entity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Expense")
        builder.setMessage(
            "Expense Name: ${expenseEntity.expenseName}\n" +
                    "Amount: ${expenseEntity.amount}\n" +
                    "Category: ${expenseEntity.category}\n" +
                    "Date: ${expenseEntity.date}\n" +
                    "Time: ${expenseEntity.time}\n" +
                    "Payment Type: ${expenseEntity.paymentType}\n" +
                    "Note: ${expenseEntity.notes.ifEmpty { "-" }}"
        )

        builder.setPositiveButton("Confirm") { _, _ ->
            viewExpenseActivity.insert(expenseEntity)
            showToast("Expense added")
            clearInputs()
        }

        builder.setNegativeButton("Edit") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
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
        findViewById<EditText>(R.id.etNotes).text.clear()
    }
}
