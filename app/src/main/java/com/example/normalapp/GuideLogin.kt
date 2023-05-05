package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GuideLogin : AppCompatActivity() {

    private lateinit var btnLoginG :Button
    private lateinit var btnSingupG:TextView
    private lateinit var tvPassword: EditText
    private lateinit var tvEmail : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_login)
// Initialize Firebase Auth
        auth = Firebase.auth

        tvEmail = findViewById(R.id.tvGusername)
        tvPassword=findViewById(R.id.tvGpasswordlogin)
        btnLoginG =findViewById(R.id.btnGlogin)

        btnLoginG.setOnClickListener{

            val gEmail =tvEmail.text.toString().trim()
            val gPassword = tvPassword.text.toString().trim()

            auth.signInWithEmailAndPassword(gEmail, gPassword)
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


       val btnSingupG = findViewById<TextView>(R.id.gSingupbtn)
        btnSingupG.setOnClickListener{
            val intent = Intent(this,GuideRegister1::class.java)
            startActivity(intent)
            }
    }

    private fun updateUI() {

            val intent = Intent(this@GuideLogin, GuideProfile::class.java)
            startActivity(intent)

    }
    public override fun onStart() {
        super.onStart()
        val currentUser =auth.currentUser
        if(currentUser != null){
            updateUI()
        }
    }
}
