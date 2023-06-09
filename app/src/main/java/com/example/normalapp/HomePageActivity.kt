package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.testapp_for_assiment.Activities.addproduct

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val firstActButton = findViewById<ImageButton>(R.id.driverButton)
        firstActButton.setOnClickListener{
            val Intent = Intent(this,DriverLoginActivity::class.java)
            startActivity(Intent)
        }

         val secondActButton = findViewById<ImageButton>(R.id.hotelButton)
        secondActButton.setOnClickListener{
            val Intent = Intent(this,addproduct::class.java)
            startActivity(Intent)
        }
        val thirdActButton = findViewById<ImageButton>(R.id.customerButton)
        thirdActButton.setOnClickListener{
            val Intent = Intent(this,CMainActivity::class.java)
            startActivity(Intent)
            }
        val forthActButton = findViewById<ImageButton>(R.id.guideButton)
        forthActButton.setOnClickListener{
            val Intent = Intent(this,GuideLogin::class.java)
            startActivity(Intent)
        }

        val placesButton =findViewById<ImageButton>(R.id.placesBtn)
        placesButton.setOnClickListener {
            val Intent =Intent(this,PlacesHomeActivity::class.java)
            startActivity(Intent)
        }

    }
}