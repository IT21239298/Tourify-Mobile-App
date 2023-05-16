package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GuideRegister1 : AppCompatActivity() {

    private lateinit var tvGemail:EditText
    private lateinit var tvGpassword:EditText
    private lateinit var tvNameG:EditText
    private lateinit var tvNicG:EditText
    private lateinit var tvContactNoG:EditText
    private lateinit var tvAddressG:EditText
    private lateinit var  tvDistrictG:EditText
    private lateinit var tvGlanguage:EditText
    private lateinit var tvGspecialiazation:EditText
    private lateinit var tvGdescription:EditText
    private lateinit var tvGbank:EditText
    private lateinit var tvGbranch:EditText
    private lateinit var tvGaccount:EditText
    private lateinit var btnNextG:Button
    private lateinit var auth: FirebaseAuth

    private lateinit var gEmail:String
    private lateinit var gPassword:String
    private lateinit var gFullname:String
    private lateinit var gNIC:String
    private lateinit var gContactnumber:String
    private lateinit var gAddress:String
    private lateinit var gDistrict:String
    private lateinit var gLanguage:String
    private lateinit var gSpecialization:String
    private lateinit var gDescription:String
    private lateinit var gBank:String
    private lateinit var gBranch:String
    private lateinit var gAccount:String

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_register1)

// Initialize Firebase Auth
        auth = Firebase.auth
        database = Firebase.database.reference


        tvNameG=findViewById(R.id.tvNameG)
        tvNicG=findViewById(R.id.tvNicG)
        tvContactNoG=findViewById(R.id.tvContactNoG)
        tvAddressG=findViewById(R.id.tvAddressG)
        tvDistrictG=findViewById(R.id.tvDistrictG)
        btnNextG=findViewById(R.id.nextGBtn1)
        tvGemail =findViewById(R.id.tvGemail)
        tvGpassword=findViewById(R.id.tvGpassword)
        tvGlanguage=findViewById(R.id.tvGlanguage)
        tvGspecialiazation=findViewById(R.id.tvGspecialiazation)
        tvGdescription=findViewById(R.id.tvGdescription)
        tvGdescription=findViewById(R.id.tvGdescription)
        tvGbank=findViewById(R.id.tvGbank)
        tvGbranch=findViewById(R.id.tvGbranch)
        tvGaccount=findViewById(R.id.tvGaccount)

        btnNextG.setOnClickListener {

            val gEmail = tvGemail.text.toString().trim()
            val gPassword = tvGpassword.text.toString().trim()

            auth.createUserWithEmailAndPassword(gEmail, gPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                     
                        val user = auth.currentUser
                        saveData()
                       // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                      
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                       //updateUI(null)
                    }
                }

        }
    }

    private fun saveData() {

        gFullname=tvNameG.text.toString().trim()
        gNIC=tvNicG.text.toString().trim()
        gContactnumber=tvContactNoG.text.toString().trim()
        gAddress=tvAddressG.text.toString().trim()
        gDistrict=tvDistrictG.text.toString().trim()
        gEmail=tvGemail.text.toString().trim()
        gLanguage=tvGlanguage.text.toString().trim()
        gSpecialization=tvGspecialiazation.text.toString().trim()
        gDescription=tvGdescription.text.toString().trim()
        gBank=tvGbank.text.toString().trim()
        gBranch=tvGbranch.text.toString().trim()
        gAccount=tvGaccount.text.toString().trim()

        val GuidRegValidation = GuidRegValidation()

        if(GuidRegValidation.GuidRegValidateFeild(gFullname,gNIC,gContactnumber,gAddress,gDistrict,gEmail,gLanguage,gSpecialization,gDescription,gBank,gBranch,gAccount)){

            val user =UserGuideModel(gFullname,gNIC,gContactnumber,gAddress,gDistrict,gEmail,gLanguage,gSpecialization,gDescription,gBank,gBranch,gAccount)
            val userID =FirebaseAuth.getInstance().currentUser!!.uid
            database.child("User").child(userID).setValue(user)

            val intent =Intent(this@GuideRegister1,GuideProfile:: class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"All Feild Required",Toast.LENGTH_SHORT).show()
        }

    }


}