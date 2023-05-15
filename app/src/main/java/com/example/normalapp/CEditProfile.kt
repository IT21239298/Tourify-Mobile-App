package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView


class CEditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cedit_profile)

        val backButton: ImageView = findViewById(R.id.imageView4)
        backButton.setOnClickListener {
            // Start the main activity
            val intent = Intent(this, CMainActivity::class.java)
            startActivity(intent)
        }

            val editButton = findViewById<Button>(R.id.cEditbtn1)
            editButton.setOnClickListener {
                // Get the entered data from the EditText fields
                val name = findViewById<EditText>(R.id.CEprofile1).text.toString()
                val country = findViewById<EditText>(R.id.CEprofile2).text.toString()
                val email = findViewById<EditText>(R.id.CEprofile3).text.toString()

                // Start the CustProfile activity and pass the entered data as extras
                val intent = Intent(this, CustProfile::class.java)
                intent.putExtra("name", name)
                intent.putExtra("country", country)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }
    }
