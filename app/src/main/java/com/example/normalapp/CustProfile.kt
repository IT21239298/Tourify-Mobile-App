package com.example.normalapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustProfile : AppCompatActivity() {
    private lateinit var usersRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cust_profile)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Get the user data from Intent extras
        val name = intent.getStringExtra("name")
        val country = intent.getStringExtra("country")
        val email = intent.getStringExtra("email")

        // Use the user data as needed in your CustProfile activity
        // For example, you can set the text of TextViews to display the data
        val nameTextView: TextView = findViewById(R.id.cprofilet2)
        val countryTextView: TextView = findViewById(R.id.cprofilet3)
        val emailTextView: TextView = findViewById(R.id.cprofilet4)

        nameTextView.text = name
        countryTextView.text = country
        emailTextView.text = email

        // Get reference to the users node in the Firebase Realtime Database
        usersRef = FirebaseDatabase.getInstance().getReference("users")

        val backButton = findViewById<ImageView>(R.id.imageView2)
        backButton.setOnClickListener {
            // Start the MainActivity
            val intent = Intent(this, CMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        val editButton = findViewById<Button>(R.id.cpbtn1)
        editButton.setOnClickListener {
            // Start the EditProfile activity
            val intent = Intent(this, CEditProfile::class.java)
            startActivity(intent)

        }

        val deleteButton = findViewById<Button>(R.id.cpbtn2)
        deleteButton.setOnClickListener {
            // Clear the entered data and start the CRegister activity
            val intent = Intent(this, CRegister::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        val assignDriverButton = findViewById<Button>(R.id.cpbtn3)
        assignDriverButton.setOnClickListener {
            // Start the CAssignDriverProfile activity
            val intent = Intent(this, CAssignDriver::class.java)
            startActivity(intent)

        }

    }


}