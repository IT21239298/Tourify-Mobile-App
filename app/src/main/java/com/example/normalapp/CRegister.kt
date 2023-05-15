package com.example.normalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CRegister : AppCompatActivity() {

    private lateinit var usersRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cregister)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Get reference to the back arrow ImageView
        val backArrow: ImageView = findViewById(R.id.imageView3)

        // Set click listener for the back arrow ImageView
        backArrow.setOnClickListener {
            // Start the main activity
            val intent = Intent(this, CMainActivity::class.java)
            startActivity(intent)
        }

        // Get reference to the REGISTER button
        val registerButton: Button = findViewById(R.id.cRegisterbtn1)

        // Initialize the Firebase Realtime Database
        usersRef = FirebaseDatabase.getInstance().getReference("users")

        // Set click listener for the REGISTER button
        registerButton.setOnClickListener {
            // Get user input from EditText fields
            val name = findViewById<EditText>(R.id.custRegister3).text.toString()
            val country = findViewById<EditText>(R.id.custRegister4).text.toString()
            val email = findViewById<EditText>(R.id.custRegister5).text.toString()
            val password = findViewById<EditText>(R.id.custRegister6).text.toString()

            // Create a new child node in the "users" node with a unique key
            val newUserRef = usersRef.push()

            // Store the user details as a User object
            val user = User(name, country, email, password)

            // Save the user object to the database
            newUserRef.setValue(user)

            // Start the CustProfile activity and pass user data as extras
            val intent = Intent(this, CustProfile::class.java)
            intent.putExtra("name", name)
            intent.putExtra("country", country)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}

data class User(
    val name: String,
    val country: String,
    val email: String,
    val password: String
)