package com.example.normalapp

import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class DriverRegActivity : AppCompatActivity() {

        private lateinit var driverEmail:TextView
        private lateinit var driverpwd:TextView
        private lateinit var driverName:TextView
        private lateinit var driverBirth:TextView
        private lateinit var driverGender:TextView
        private lateinit var driverAddres:TextView
        private lateinit var btnDriverReg:Button
        private lateinit var  vehicleaNumber: TextView
        private lateinit var vehicleaType:TextView
        private lateinit var  vehicleaInsuarance:TextView


    private lateinit var auth: FirebaseAuth

    private lateinit var  sEmail:String
    private lateinit var  sPassword:String
    private lateinit var   sName:String
    private lateinit var sAddress: String
    private lateinit var sBirth:String
    private lateinit var sGender:String
    private lateinit var vNumber:String
    private lateinit var vType:String
    private lateinit var vInsuarance:String

    private lateinit var database: DatabaseReference
// ...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_reg)

        // Initialize Firebase Auth
        auth = Firebase.auth

        database = Firebase.database.reference



        driverEmail = findViewById(R.id.driverEmail)
        driverpwd = findViewById(R.id.driverpwd)
        driverName = findViewById(R.id.driverName)
        driverBirth = findViewById(R.id.driverBirth)
        driverGender = findViewById(R.id.driverGender)
        driverAddres = findViewById(R.id.driverRegaddress)
        vehicleaNumber= findViewById(R.id.dvehiclenumber)
        vehicleaType= findViewById(R.id.dvehicletype)
        vehicleaInsuarance= findViewById(R.id.dvehicleinsuarnce)
        btnDriverReg = findViewById(R.id.driverbtn)


        btnDriverReg.setOnClickListener{


            val sEmail  = driverEmail.text.toString().trim()
            val sPassword = driverpwd.text.toString().trim()



            auth.createUserWithEmailAndPassword( sEmail,  sPassword)
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
                     //   updateUI(null)
                    }
                }
        }

    }

    private fun saveData() {



        sEmail = driverEmail.text.toString().trim()
        sName = driverName.text.toString().trim()
        sAddress = driverAddres.text.toString().trim()
        sBirth = driverBirth.text.toString().trim()
        sGender = driverGender.text.toString().trim()
        vNumber = vehicleaNumber.text.toString().trim()
        vType = vehicleaType.text.toString().trim()
        vInsuarance = vehicleaInsuarance.text.toString().trim()

        val DriverRegValidation = DriverRegValidation()

        if (DriverRegValidation.DriverRegValidateFeild( sName , sBirth,  sAddress, sGender ,sEmail,vNumber,vType,vInsuarance)){
            val userDriver = UserDriverModel(sEmail,sName,sAddress,sBirth,sGender,vNumber,vType,vInsuarance)

            val userDriverID = FirebaseAuth.getInstance().currentUser!!.uid
            database.child("UserDriver").child(userDriverID).setValue(userDriver)

            val intent = Intent(this,DashboardActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"All Feild are required..!",Toast.LENGTH_SHORT).show()
        }





    }


}