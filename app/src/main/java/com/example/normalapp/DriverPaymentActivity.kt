package com.example.normalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class DriverPaymentActivity : AppCompatActivity() {

    private lateinit var uniqHour: TextView
    private lateinit var uniqKm: TextView
    private lateinit var cusName: TextView
    private lateinit var enterhour: TextView
    private lateinit var enterKm: TextView
    private lateinit var enterAdditionalfee: TextView
    private lateinit var total: TextView
    private lateinit var btnCalculate: Button

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_payment)

        database = Firebase.database.reference

        uniqHour = findViewById(R.id.hourPayment)
        uniqKm = findViewById(R.id.kmPayment)
        cusName = findViewById(R.id.cusNamePayment)
        enterhour = findViewById(R.id.enterhourpayment)
        enterKm = findViewById(R.id.enterkmPaymnt)
        enterAdditionalfee = findViewById(R.id.additinalpayment)
        total = findViewById(R.id.totalpayment)
        btnCalculate = findViewById(R.id.calculatebtn)

        btnCalculate.setOnClickListener {
            savePayment()
        }
    }

    private fun savePayment() {
        val duniqHour = uniqHour.text.toString().trim()
        val duniqKm = uniqKm.text.toString().trim()
        val dcusName = cusName.text.toString().trim()
        val denterhour = enterhour.text.toString().trim()
        val denterKm = enterKm.text.toString().trim()
        val denterAdditionalfee = enterAdditionalfee.text.toString().trim()


        val totalValue = (denterhour.toDoubleOrNull() ?: 0.0) * (duniqHour.toDoubleOrNull()?:0.0) +
                (denterKm.toDoubleOrNull() ?: 0.0) * ( duniqKm.toDoubleOrNull()?:0.0) +
                (denterAdditionalfee.toDoubleOrNull() ?: 0.0)


        total.text = totalValue.toString()

        if (totalValue == 0.0) {
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("DriverPayments")
        val paymentID = ref.push().key
        val userDriverPayment = paymentID?.let {
            DriverPaymentModel(
                it,
                duniqHour,
                duniqKm,
                dcusName,
                denterhour,
                denterKm,
                denterAdditionalfee,
                totalValue.toString()
            )
        }

        if (paymentID != null) {
            ref.child(paymentID).setValue(userDriverPayment)
        }

    }
}
