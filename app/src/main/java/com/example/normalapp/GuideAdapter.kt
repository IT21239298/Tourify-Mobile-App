package com.example.normalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase


class GuideAdapter(val guideList:ArrayList<TourTypes>): RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {
    class GuideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTourTypes:TextView=itemView.findViewById(R.id.packageTourTypetv)
        val  tvDescription:TextView = itemView.findViewById(R.id.packageDescriptiontv)
        val tvprice:TextView=itemView.findViewById(R.id.packagePricetv)
        val btndelte:Button =itemView.findViewById(R.id.deletebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
       val itemView= LayoutInflater.from(parent.context).inflate(R.layout.guide_service_list,parent,false)
        return GuideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {

        val currentItem =guideList[position]

        holder.tvTourTypes.text=currentItem.tourType
        holder.tvDescription.text=currentItem.description
        holder.tvprice.text=currentItem.tourPrice

        holder.btndelte.setOnClickListener {
            val databaseRef = FirebaseDatabase.getInstance().getReference("GuideAddPackages")
            val itemId= currentItem.packagesID

            if(itemId != null){
                databaseRef.child(itemId).removeValue()

            }

            guideList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,guideList.size)
        }

    }
    override fun getItemCount(): Int {
        return guideList.size

    }


}

