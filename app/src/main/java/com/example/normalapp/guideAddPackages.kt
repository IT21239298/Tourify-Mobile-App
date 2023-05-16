package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class guideAddPackages : AppCompatActivity() {
    private lateinit var tour : Spinner
    private lateinit var tvPdescription: EditText
    private lateinit var tvPprice: EditText
    private lateinit var btnAdd : Button


    private lateinit var database: DatabaseReference

    val packageType = arrayOf(" select package type", "private tour", "Group tour")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_add_packages)

        database = Firebase.database.reference

        tour = findViewById<Spinner>(R.id.guidepackagetypetv)
        tvPdescription=findViewById(R.id.packageDescriptiontv)
        tvPprice=findViewById(R.id.tourPricetv)
        btnAdd=findViewById(R.id.btnAddTour)

        btnAdd.setOnClickListener {
            saveAddPackages()
            val intent = Intent(
                this@guideAddPackages,GuideServices::class.java)
            startActivity(intent)
        }




        //implementing spinner

        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, packageType)
        tour.adapter = arrayAdapter
        tour.setSelection(0, false)
        tour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(
                    applicationContext,
                    "Selected package: " + packageType[p2],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Please select an item", Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
private fun saveAddPackages() {
    val gTourtype = tour.selectedItem.toString().trim()
    val gDescription=tvPdescription.text.toString().trim()
    val gPrice=tvPprice.text.toString().trim()

    val ref = FirebaseDatabase.getInstance().getReference("GuideAddPackages")
    val  packagesID = ref.push().key
    val guidePackages = packagesID ?.let{
        guidePackageModel(
             it,
            gTourtype,
            gDescription,
            gPrice,
        )
    }

    if( packagesID  != null){
        ref.child( packagesID ).setValue(guidePackages)
    }
}



}
