package com.example.testapp_for_assiment.Activities

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.testapp_for_assiment.Models.ProductModel
import com.example.testapp_for_assiment.R
import com.google.firebase.database.*

class Updateprduct : AppCompatActivity() {

    private lateinit var database:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateproduct)

        database = FirebaseDatabase.getInstance().reference

        val cat=findViewById<EditText>(R.id.ucat)
        val name=findViewById<EditText>(R.id.uname)
        val qty=findViewById<EditText>(R.id.uqty)
        val desc=findViewById<EditText>(R.id.udesc)
        val price=findViewById<EditText>(R.id.uprice)
        val updateBtn=findViewById<Button>(R.id.update)

        var id = intent.getStringExtra("proId").toString()

        database.child("products").child(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(ProductModel::class.java)

                cat.setText(user?.proCat).toString()
                name.setText(user?.proName).toString()
                qty.setText(user?.proQty).toString()
                desc.setText(user?.proDesc).toString()
                price.setText(user?.proprice).toString()

                println(user?.proID.toString())
                println(user?.proName.toString())
                println(user?.proDesc.toString())
                println(user?.proprice.toString())
                println(user?.proprice.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        updateBtn.setOnClickListener{
               val newcat=cat.text.toString()
               val newname=name.text.toString()
               val newqty=qty.text.toString()
               val newdesc=desc.text.toString()
               val newprice=price.text.toString()

            updateDetail(newcat,newname,newqty,newdesc,newprice)
        }

    }

    fun updateDetail(cat: String, name:String, qty:String, desc:String, price:String){
        val updates= mapOf<String,String>(
            "proCat" to cat,
            "proName" to name,
            "proQty" to qty,
            "proDesc" to desc,
            "proPrice" to price)

        var id = intent.getStringExtra("proId").toString()

        database.child("Products").child(id).updateChildren(updates)
            .addOnSuccessListener {
                Toast.makeText(this,"update details Success",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(this,"Details Update Unsuccess",Toast.LENGTH_LONG).show()
            }
    }


}