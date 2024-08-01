package com.example.asg2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var viewExpenseActivity: ViewExpenseActivity


    @SuppressLint("SetTextI18n", "DefaultLocale", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_add_expense)
        //viewExpenseActivity = ViewModelProvider(this)[ViewExpenseActivity::class.java]

    }


}
