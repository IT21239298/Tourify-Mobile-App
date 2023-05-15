package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class CAssignDriver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cassign_driver)

        val backButton: ImageView = findViewById(R.id.imageView6)
        backButton.setOnClickListener {
            // Start the main activity
            val intent = Intent(this, CMainActivity::class.java)
            startActivity(intent)
        }




        val assignDriverButton = findViewById<Button>(R.id.CADriverbtn2)
        assignDriverButton.setOnClickListener {
            // Start the CBookDriverProfile activity
            val intent = Intent(this, CBookDriver::class.java)
            startActivity(intent)
        }
    }
}