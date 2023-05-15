package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddPlaces : AppCompatActivity() {

    private lateinit var  pName : EditText
    private lateinit var pLocation :EditText
    private lateinit var database: DatabaseReference
    private lateinit var btnAddp:Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_places)

        database = Firebase.database.reference

        pName =findViewById(R.id.placenametv)
        pLocation=findViewById(R.id.placelocationtv)
        btnAddp=findViewById(R.id.btnAddPlaces)

        btnAddp.setOnClickListener {

            saveAddplaces()
            val intent = Intent(
                this@AddPlaces,PlacesHomeActivity::class.java)
            startActivity(intent)


        }



    }
    private fun saveAddplaces() {
        val plName = pName.text.toString().trim()
        val plLocation=pLocation.text.toString().trim()
        val ref = FirebaseDatabase.getInstance().getReference("Places")
        val placesID =ref.push().key
        val places = placesID?.let{
            PlacesModel(
                plName,
                plLocation,
            )
        }
        if(placesID != null){
            ref.child(placesID).setValue(places)
        }

    }
}