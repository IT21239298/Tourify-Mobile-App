package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class PlacesHomeActivity : AppCompatActivity() {


    private lateinit var recyclerView :RecyclerView
    private lateinit var  placesArrayList: ArrayList<ViewPlaces>
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_home)

        recyclerView =findViewById(R.id.placeRecycleview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        placesArrayList= arrayListOf()

        val addbutton =findViewById<Button>(R.id.addPbtn)
        addbutton.setOnClickListener {

                val Intent =Intent(this,AddPlaces::class.java)
                startActivity(Intent)

        }

        database = FirebaseDatabase.getInstance().getReference("Places")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(dataSnapShot in snapshot.children){
                        val place = dataSnapShot.getValue(ViewPlaces :: class.java)
                        if(!placesArrayList.contains(place)){
                            placesArrayList.add(place!!)
                        }
                    }

                    recyclerView.adapter=PlacesAdapter(placesArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PlacesHomeActivity ,error.toString(), Toast.LENGTH_SHORT).show()

            }

        })




        }
}

