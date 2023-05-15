package com.example.normalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PlacesAdapter(val placesList: ArrayList<ViewPlaces>):RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>() {
    class PlaceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val plName:TextView =itemView.findViewById(R.id.pNametv)
        val plLocation:TextView=itemView.findViewById(R.id.pLocationtv)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.places_list,parent,false)
        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.plName.text=placesList[position].name
        holder.plLocation.text=placesList[position].location
    }
    override fun getItemCount(): Int {
        return placesList.size
    }




}