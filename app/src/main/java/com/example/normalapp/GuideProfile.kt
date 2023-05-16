package com.example.normalapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GuideProfile : AppCompatActivity() {
    private lateinit var  btnServiceG: ImageButton
    private lateinit var btnPaymentG:ImageButton
    private lateinit var btnEditG:Button
    private lateinit var  tvGemail:TextView
    private lateinit var tvGAddress:TextView
    private lateinit var tvContactNoG:TextView
    private lateinit var tvGspecialiazation:TextView
    private lateinit var tvGdescription:TextView
    private lateinit var btnLogout:Button

    private lateinit var database: DatabaseReference
// ...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_profile)

        database = Firebase.database.reference
        tvGemail= findViewById(R.id.profileGemail)
        tvGAddress=findViewById(R.id.pGAddress)
        tvContactNoG=findViewById(R.id.profileGcontactnumber)
        tvGspecialiazation=findViewById(R.id.profileGspecialization)
        tvGdescription=findViewById(R.id.profileGdescription)
        btnEditG=findViewById(R.id.GprofileEditbtn)
        btnPaymentG=findViewById(R.id.payementGbtn)
        btnServiceG=findViewById(R.id.servicesGbtn2)

        btnLogout =findViewById(R.id.logoutbtn)

        btnLogout.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent(this@GuideProfile,HomePageActivity::class.java)
            startActivity(intent)
        }

        var userID =FirebaseAuth.getInstance().currentUser!!.uid

        database.child("User").child(userID).get().addOnSuccessListener {


            val email=it.child("email").value.toString()
            val address=it.child("address").value.toString()
            val contactNumber=it.child("contactNumber").value.toString()
            val specialization =it.child("specialization").value.toString()
            val description =it.child("description").value.toString()

            tvGemail.text = email
            tvGAddress.text=address
            tvContactNoG.text=contactNumber
            tvGspecialiazation.text=specialization
            tvGdescription.text=description

      }.addOnFailureListener{
          Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()

        }

       btnEditG.setOnClickListener {
           val bottomSheet =BottomSheetDialog(this)
           bottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE)
           bottomSheet.setContentView(R.layout.activity_guide_edit_sheet)
           bottomSheet.window!!.setLayout(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT
           )
           bottomSheet.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

           val btnCancel :Button? =bottomSheet.findViewById(R.id.Gcancelbtn)
           val btnEdited :Button?=bottomSheet.findViewById(R.id.Geditbtn)

           val etGemail:EditText? =bottomSheet.findViewById(R.id.editEmail)
           val etGAddress :EditText?=bottomSheet.findViewById(R.id.editPasswordG)
           val etGcontactnumber :EditText?=bottomSheet.findViewById(R.id.editContactNoG)
           val etGspecializedarea:EditText?=bottomSheet.findViewById(R.id.editSppecilaizedG)
           val etGdescription:EditText?=bottomSheet.findViewById(R.id.editDescriptionG)

           val gEmail = tvGemail.text.toString()
           val gAddress =tvGAddress.text.toString()
           val gContactnumber =tvContactNoG.text.toString()
           val gSpecialization =tvGspecialiazation.text.toString()
           val gDescription = tvGdescription.text.toString()

           Log.d("testing" ,"onCreate: $gEmail$gAddress$gContactnumber$gSpecialization$gDescription")

           etGemail!!.setText(gEmail)
           etGAddress!!.setText(gAddress)
           etGcontactnumber!!.setText(gContactnumber)
           etGspecializedarea!!.setText(gSpecialization)
           etGdescription!!.setText(gDescription)

           btnCancel!!.setOnClickListener{
               bottomSheet.dismiss()
           }
           btnEdited!!.setOnClickListener {
               val email =etGemail!!.text.toString()
               val password=etGAddress!!.text.toString()
               val contactNumber=etGcontactnumber!!.text.toString()
               val  specialization=etGspecializedarea!!.text.toString()
               val description =etGdescription!!.text.toString()

              val editMap = mapOf(
                   "email" to email,
              "address" to password,
                  "contactNumber" to contactNumber,
              "specialization" to specialization,
              "description" to description,
              )
                  val userID = FirebaseAuth.getInstance().currentUser!!.uid
               database.child("User").child(userID).updateChildren(editMap)



               Toast.makeText(this,"Edited successfully" ,Toast.LENGTH_SHORT)
               bottomSheet.dismiss()
           }
           bottomSheet.show()

}
        btnServiceG.setOnClickListener{
            val intent = Intent(this@GuideProfile,GuideServices::class.java)
            startActivity(intent)
        }

        btnPaymentG.setOnClickListener{
            val intent =Intent(
                this@GuideProfile,GuidePayment::class.java)
            startActivity(intent)

        }
    }
}