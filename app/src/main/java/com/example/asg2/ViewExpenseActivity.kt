package com.example.asg2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ViewExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)

        val btnBackToMainMenu: Button = findViewById(R.id.btnBackToMainMenu)
        btnBackToMainMenu.setOnClickListener {
            // Finish the current activity and return to the previous one
            finish()
        }
    }
}
