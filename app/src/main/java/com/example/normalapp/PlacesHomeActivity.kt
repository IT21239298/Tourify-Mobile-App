package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlacesHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val addbutton =findViewById<Button>(R.id.addPbtn)
        addbutton.setOnClickListener {

                val Intent =Intent(this,AddPlaces::class.java)
                startActivity(Intent)

        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_home)
    }
}

