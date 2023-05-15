package com.example.normalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class DriverVehicleActivity : AppCompatActivity() {

    private lateinit var drivervNumber: TextView
    private lateinit var drivervType:TextView
    private lateinit var drivervInsuarance:TextView

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_vehicle)

        database = Firebase.database.reference

        drivervNumber = findViewById(R.id.dvehicleNumber)
        drivervType = findViewById(R.id.dvehicType)
        drivervInsuarance = findViewById(R.id.dvehicleInsuarance)

        val userDriverID = FirebaseAuth.getInstance().currentUser!!.uid

        database.child("UserDriver").child(userDriverID).get().addOnSuccessListener {

            val vehicleNumber= it.child("vnumber").value.toString()
            val vehicleType = it.child("vtype").value.toString()
            val vehicleInsuarance = it.child("vinsuarance").value.toString()

            drivervNumber.text = vehicleNumber
            drivervType.text =   vehicleType
                drivervInsuarance.text =  vehicleInsuarance

        }.addOnFailureListener{
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}