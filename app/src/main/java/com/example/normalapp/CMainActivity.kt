package com.example.normalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class CMainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cmain)

        val backButton: ImageView = findViewById(R.id.imageView)
        backButton.setOnClickListener {
            // Start the main activity
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.cust3)
        val passwordEditText = findViewById<EditText>(R.id.cust4)

        val loginButton = findViewById<Button>(R.id.custb1)
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            login(email, password)
        }

        val signUpButton = findViewById<TextView>(R.id.custb2)
        signUpButton.setOnClickListener {
            // Start the CRegister activity
            val intent = Intent(this, CRegister::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Start the customer profile activity and pass the email as an extra
                    val intent = Intent(this, CustProfile::class.java)
                    intent.putExtra("email", user?.email)
                    startActivity(intent)
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> "Invalid password"
                        else -> "Authentication failed"
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
