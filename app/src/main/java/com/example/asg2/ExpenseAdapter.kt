package com.example.asg2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    // Callback function for item click
    private val onItemClick: (Expense) -> Unit,
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    // List of expenses
    private var expens = listOf<Expense>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        // Inflate the item layout
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_view_expense, parent, false)
        // Return a new ViewHolder
        return ExpenseViewHolder(itemView)
    }

    // Bind data to the ViewHolder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expens[position]
        holder.expenseName.text = currentExpense.expenseName
        holder.amount.text = currentExpense.amount.toString()
        holder.category.text = currentExpense.category
        holder.date.text = currentExpense.date
        holder.time.text = currentExpense.time
        holder.paymentType.text = currentExpense.paymentType

        // Handle item click
        holder.itemView.setOnClickListener {
            onItemClick(currentExpense)
        }
    }

    // Return the number of expenses
    override fun getItemCount(): Int {
        return expens.size
    }

    // Update the list of expenses
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newExpens: List<Expense>) {
        expens = newExpens
        notifyDataSetChanged()
    }

    // ViewHolder class
    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseName: TextView = itemView.findViewById(R.id.expense_name)
        val amount: TextView = itemView.findViewById(R.id.amount)
        val category: TextView = itemView.findViewById(R.id.category)
        val date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
        val paymentType: TextView = itemView.findViewById(R.id.payment_type)

    }
}
