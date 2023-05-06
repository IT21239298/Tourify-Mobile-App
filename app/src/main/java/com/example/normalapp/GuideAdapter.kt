package com.example.normalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GuideAdapter(val guideList:ArrayList<TourTypes>): RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {
    class GuideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val  tvDescription:TextView = itemView.findViewById(R.id.packageDescriptiontv)
        val tvprice:TextView=itemView.findViewById(R.id.tourPricetv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
       val itemView= LayoutInflater.from(parent.context).inflate(R.layout.guide_service_list,parent,false)
        return GuideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {

        holder.tvDescription.text=guideList[position].description
        holder.tvprice.text=guideList[position].tourPrice

    }
    override fun getItemCount(): Int {
        return guideList.size

    }


}

