package com.example.testapp_for_assiment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp_for_assiment.Models.ProductModel
import com.example.testapp_for_assiment.R

class ProductAdapter(private val productList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.add_data_rows, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentpro = productList[position]

        holder.tvname.text = currentpro.proName
        holder.tvqty.text = currentpro.proQty
        holder.tvprice.text = currentpro.proprice
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {

        val tvname = itemView.findViewById<TextView>(R.id.nameview)
        val tvqty= itemView.findViewById<TextView>(R.id.qtyview)
        val tvprice = itemView.findViewById<TextView>(R.id.priceview)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }
}
