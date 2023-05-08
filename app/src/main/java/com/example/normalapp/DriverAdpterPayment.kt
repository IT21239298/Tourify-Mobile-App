package com.example.normalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class DriverAdpterPayment (val driverPaymentList:ArrayList< DPaymentList>):RecyclerView.Adapter< DriverAdpterPayment.DriverViewHolder>() {

    class DriverViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvName : TextView = itemView.findViewById(R.id.totalinName)
        val tvTotal:TextView = itemView.findViewById(R.id.totalvalue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.driver_payment_list,parent,false)
        return DriverViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.tvName.text = driverPaymentList[position].cusName
        holder.tvTotal.text = driverPaymentList[position].total
    }

    override fun getItemCount(): Int {
        return driverPaymentList.size
    }


}