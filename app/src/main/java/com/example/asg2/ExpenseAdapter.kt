package com.example.asg2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asg2.Data.Expense

class ExpenseAdapter(context: Context, expenseList: ArrayList<Array<String>>) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    // List of expenses
    private var expens = listOf<Expense>()

    //Changed here
    private val mContext = context // Context passed in
    private val mExpenseList = expenseList

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        // Inflate the item layout
        val view = LayoutInflater.from(mContext).inflate(R.layout.activity_view_expense, parent, false)
        // Return a new ViewHolder
        return ExpenseViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Return the size of the list
        return mExpenseList.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        // Get the data at the position
        val expense = mExpenseList[position]
        // Set the data
        holder.expenseName.text = expense[0]
        holder.amount.text = expense[1]
        //holder.category.text = expense[2]
        holder.date.text = expense[3]
        //holder.time.text = expense[4]
        //holder.paymentType.text = expense[5]
    }
}
