package com.example.asg2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val btViewExpense: Button = findViewById(R.id.btViewExpense)
        val btAddExpense: Button = findViewById(R.id.btAddExpense)
        val btExit: Button = findViewById(R.id.btExit)

        // Set click listeners for buttons
        btViewExpense.setOnClickListener {
            val intent = Intent(this, ViewExpenseActivity::class.java)
            startActivity(intent)
        }

        btAddExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        btExit.setOnClickListener {
            finishAffinity() // Close the app
        }
    }
}
