package com.example.normalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GuidePayment : AppCompatActivity() {

    private lateinit var noOfDaysG:TextView
    private lateinit var pricePerDay:TextView
    private lateinit var tourType:TextView
    private lateinit var accomadationCost:TextView
    private lateinit var Gcalculatebtn:Button
    private lateinit var totalG:TextView

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_payment)

        database = Firebase.database.reference

        noOfDaysG =findViewById(R.id.GnoOfDays)
        pricePerDay=findViewById(R.id.GpayDay)
        tourType=findViewById(R.id.Gpaytour)
        accomadationCost=findViewById(R.id.Gpayaccomadation)
        Gcalculatebtn=findViewById(R.id.btnCalculateG)
        totalG=findViewById(R.id.guideTotal)

        Gcalculatebtn.setOnClickListener {
            saveGuidePayment()
        }
    }

    private fun saveGuidePayment() {
        val  gnoOfDays = noOfDaysG.text.toString().trim()
        val gpricePerday =pricePerDay.text.toString().trim()
        val gtourType = tourType.text.toString().trim()
        val gaccomadation = accomadationCost.text.toString().trim()

        val totalPayment = (gnoOfDays.toIntOrNull() ?: 0)*(gpricePerday.toDoubleOrNull()?:0.0)+(gtourType.toDoubleOrNull() ?:0.0)+(gaccomadation.toDoubleOrNull()?:0.0)
        totalG.text = totalPayment.toString()


        if(totalPayment == 0.0){
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            return

        }

        val ref =FirebaseDatabase.getInstance().getReference(("GuidePayments"))
        val guidePaymentID = ref.push().key
        val guidePayment = guidePaymentID?.let{
            guidePaymentModel(
                it,
                gnoOfDays,
                gpricePerday,
                gtourType,
                gaccomadation,
                totalPayment.toString()

            )
        }
        if (guidePaymentID != null){
            ref.child(guidePaymentID).setValue(guidePayment)
        }

    }
}