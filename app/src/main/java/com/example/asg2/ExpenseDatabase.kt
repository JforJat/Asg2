package com.example.asg2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    // Define abstract methods for each DAO
    abstract fun expenseDao(): ExpenseDao

    // Singleton pattern to ensure only one instance of the database
    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        // Get the database instance
        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ExpenseDatabase::class.java, "expense_database"
                ).build()
                // Assign the instance to the INSTANCE variable
                INSTANCE = instance
                instance
            }
        }
    }
}
