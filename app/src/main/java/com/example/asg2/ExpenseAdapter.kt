package com.example.asg2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(context: Context, expenseList: ArrayList<Array<String>>) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    // List of expenses
    private var expens = listOf<Expense>()

    //Changed here
    private val mContext = context // Context passed in
    private val mExpenseList = expenseList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expenseName: TextView
        var amount: TextView
        //var category: TextView
        var date: TextView
        //var time: TextView
        //var paymentType: TextView

        init {
            expenseName = itemView.findViewById(R.id.expense_name)
            amount = itemView.findViewById(R.id.expense_amount)
            //category = itemView.findViewById(R.id.category)
            date = itemView.findViewById(R.id.expense_date)
            //time = itemView.findViewById(R.id.time)
            //paymentType = itemView.findViewById(R.id.payment_type)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout
        val view = LayoutInflater.from(mContext).inflate(R.layout.activity_view_expense, parent, false)
        // Return a new ViewHolder
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Return the size of the reporter list
        return mExpenseList.size
    }

    override fun onBindViewHolder(holder: ExpenseAdapter.ViewHolder, position: Int) {
        val currentExpense = expens[position]
        holder.expenseName.text = mExpenseList[position][0]
        holder.amount.text = mExpenseList[position][1]
        //holder.category.text = currentExpense.category
        holder.date.text = mExpenseList[position][2]
        //holder.time.text = currentExpense.time
        //holder.paymentType.text = currentExpense.paymentType

    }

    // ViewHolder class
    //class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val expenseName: TextView = itemView.findViewById(R.id.expense_name)
        //val amount: TextView = itemView.findViewById(R.id.expense_amount)
        //val category: TextView = itemView.findViewById(R.id.category)
        //val date: TextView = itemView.findViewById(R.id.expense_date)
        //val time: TextView = itemView.findViewById(R.id.time)
        //val paymentType: TextView = itemView.findViewById(R.id.payment_type)
    //}
}
