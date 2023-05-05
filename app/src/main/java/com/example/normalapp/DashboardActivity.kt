package com.example.normalapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnLogOut : Button
    private lateinit var driverEmail: TextView

    private lateinit var driverName: TextView
    private lateinit var driverBirth: TextView
    private lateinit var driverGender: TextView
    private lateinit var driverAddres: TextView
    private lateinit var  btneditD : Button
    private lateinit var database: DatabaseReference
// ...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        database = Firebase.database.reference

        driverEmail = findViewById(R.id.driverProfileEmail)
        driverName = findViewById(R.id.driverProfileName)
        driverBirth = findViewById(R.id.DriverProfileBithday)
        driverGender = findViewById(R.id.DriverPorfileGender)
        driverAddres = findViewById(R.id.driverProfileAddress)
       btneditD =  findViewById(R.id.dEditbtn)
        btnLogOut  = findViewById(R.id.Logoutbtn)



        btneditD.setOnClickListener{

            val bottomSheet = BottomSheetDialog(this)
            bottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheet.setContentView(R.layout.activity_driver_edit)
            bottomSheet.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            bottomSheet.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

            val  btnCancel: Button? = bottomSheet.findViewById(R.id.btneditcancel)
            val btnEdited: Button ? = bottomSheet.findViewById(R.id.buttnedit)

            btnCancel!!.setOnClickListener{
                bottomSheet.dismiss()
            }
            btnEdited!!.setOnClickListener{
                Toast.makeText(this,"Edited successfully" ,Toast.LENGTH_SHORT).show()
                bottomSheet.dismiss()
            }
            bottomSheet.show()

        }

        val userDriverID = FirebaseAuth.getInstance().currentUser!!.uid

        database.child("UserDriver").child(userDriverID).get().addOnSuccessListener {

            val name = it.child("name").value.toString()
            val birth = it.child("birth").value.toString()
            val email = it.child("email").value.toString()
            val gender = it.child("gender").value.toString()
            val address = it.child("address").value.toString()

            driverName.text = name
            driverBirth.text = birth
            driverEmail.text = email
            driverGender.text = gender
            driverAddres.text = address
        }.addOnFailureListener{
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }


        btnLogOut.setOnClickListener{
            Firebase.auth.signOut()




            val intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent)



        }
        val secondActButton = findViewById<ImageButton>(R.id.driverpaymentbtn)
        secondActButton.setOnClickListener{
            val Intent = Intent(this,DriverPaymentActivity::class.java)
            startActivity(Intent)
        }
    }
}
