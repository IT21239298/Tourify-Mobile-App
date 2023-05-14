package com.example.normalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


private lateinit var database : DatabaseReference
class DriverAdapterPayment(private val driverPaymentList: ArrayList<DPaymentList>) : RecyclerView.Adapter<DriverAdapterPayment.DriverViewHolder>() {

    class DriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.totalinName)
        val tvTotal: TextView = itemView.findViewById(R.id.totalvalue)
        val deleteButton: ImageView = itemView.findViewById(R.id.driveracclistdeletebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dtiver_payment_list, parent, false)
        return DriverViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val currentItem = driverPaymentList[position]

        holder.tvName.text = currentItem.cusName
        holder.tvTotal.text = currentItem.total

        holder.deleteButton.setOnClickListener {
            // Delete item from Firebase
            val databaseRef = FirebaseDatabase.getInstance().getReference("DriverPayments")
            val itemId = currentItem.paymentID

            if (itemId != null) {
                databaseRef.child(itemId).removeValue()
            }

            // Remove item from the list
            driverPaymentList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, driverPaymentList.size)
        }
    }

    override fun getItemCount(): Int {
        return driverPaymentList.size
    }
}
