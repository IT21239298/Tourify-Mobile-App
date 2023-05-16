package com.example.testapp_for_assiment.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.testapp_for_assiment.Models.ProductModel
import com.example.testapp_for_assiment.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class addproduct : AppCompatActivity() {
    private lateinit var proimg:ImageView
    private lateinit var procat:EditText
    private lateinit var proname:EditText
    private lateinit var proqty:EditText
    private lateinit var prodesc:EditText
    private lateinit var proprice:EditText
    private lateinit var prosubmit:Button


    private lateinit var dbRef:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addproduct)

        proimg=findViewById(R.id.addimg)
        procat=findViewById(R.id.ucat)
        proname=findViewById(R.id.uname)
        proqty=findViewById(R.id.uqty)
        prodesc=findViewById(R.id.udesc)
        proprice=findViewById(R.id.uprice)
        prosubmit=findViewById(R.id.ubtnsubmit)


        dbRef=FirebaseDatabase.getInstance().getReference("Products")

        prosubmit.setOnClickListener{

            saveProduct()
        }
    }

    private fun saveProduct(){
        val category=procat.text.toString()
        val name=proname.text.toString()
        val quantity=proqty.text.toString()
        val description=prodesc.text.toString()
        val price=proprice.text.toString()

        if(category.isEmpty()){
            //EmpName.error="Please enter name"
        }
        if(name.isEmpty()){
            //EmpAge.error="Please enter Age"
        }
        if(quantity.isEmpty()){
            //EmpSalary.error="Please enter Salary"
        }
        if(description.isEmpty()){
            //EmpSalary.error="Please enter Salary"
        }
        if(price.isEmpty()){
            //EmpSalary.error="Please enter Salary"
        }

        val productID=dbRef.push().key!!
        val product= ProductModel(productID,category,name,quantity,description,price)

        dbRef.child(productID).setValue(product).addOnCompleteListener{
            Toast.makeText(this,"Data insert succesfully",Toast.LENGTH_LONG).show()

            val intent4 = Intent(this, Allproducts::class.java)
            startActivity(intent4)

        }.addOnFailureListener{ err->
            Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
        }
    }
}