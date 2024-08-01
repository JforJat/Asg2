package com.example.asg2.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Expense::class], version = 1, exportSchema = true)
abstract class ExpenseDatabase : RoomDatabase() {

    // Define abstract methods for each DAO
    abstract fun getExpenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var dbInstance: ExpenseDatabase? = null

        val coroutineScope = CoroutineScope(Dispatchers.IO)

        // Singleton pattern to ensure only one instance of the database
        private val dbCreationCallback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                coroutineScope.launch {
                    val expenseDao = dbInstance!!.getExpenseDao()
                    // Check if there is no default reporter
                    val expenseCount = expenseDao.getExpenseCount()

                    if (expenseCount == 0) {
                        // Add a default reporter
                        expenseDao.addExpense(
                            Expense(
                                expenseName = "Default Expense",
                                amount = 0.00,
                                category = "Default Category",
                                date = "Default Date",
                                time = "Default Time",
                                paymentType = "Default Payment Type",
                                notes = ""
                            )
                        )
                    }
                }
            }
        }

        // Get the database instance
        fun getDatabase(context: Context): ExpenseDatabase? {
            if (dbInstance == null) {
                synchronized(ExpenseDatabase::class.java) {

//                     Create the database
                    dbInstance = databaseBuilder(
                        context.applicationContext,
                        ExpenseDatabase::class.java, "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(dbCreationCallback)
                        .build()
                }
            }
            return dbInstance
        }
    }

}
