package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DriverLoginActivity : AppCompatActivity() {

    private lateinit var btnSignup:Button
    private lateinit var btnLogin:Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        btnLogin = findViewById(R.id.Loginbtn)
        etEmail = findViewById(R.id.DriverLoginEmail)
        etPassword = findViewById(R.id.DriverLoginpwd)

        btnLogin.setOnClickListener{


            val sEmail =etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()

            auth.signInWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI()
                    }
                }

        }

        val signupActButton = findViewById<TextView>(R.id.Singupbtn)
        signupActButton.setOnClickListener{
            val Intent = Intent(this,DriverRegActivity::class.java)
            startActivity(Intent)
        }
    }

    private fun updateUI() {
        val loginActButton = findViewById<Button>(R.id.Loginbtn)
        loginActButton.setOnClickListener{
            val Intent = Intent(this,DashboardActivity::class.java)
            startActivity(Intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI()
        }
    }
}