package com.example.testapp_for_assiment.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.testapp_for_assiment.Models.ProductModel
import com.example.testapp_for_assiment.R
import com.google.firebase.database.*

class viewproduct : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var id:TextView
    private lateinit var cat:TextView
    private lateinit var name:TextView
    private lateinit var quant:TextView
    private lateinit var desc:TextView
    private lateinit var price:TextView
    private lateinit var update:Button
    private lateinit var delete:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewproduct)

        id=findViewById<TextView>(R.id.pid)
        cat=findViewById<TextView>(R.id.icat)
        name=findViewById<TextView>(R.id.iname)
        quant=findViewById<TextView>(R.id.iquan)
        desc=findViewById<TextView>(R.id.idesc)
        price=findViewById<TextView>(R.id.iprice)
        update=findViewById<Button>(R.id.update)
        delete=findViewById<Button>(R.id.delete)

        id.text = intent.getStringExtra("proId")
        cat.text = intent.getStringExtra("procat")
        name.text = intent.getStringExtra("proname")
        quant.text = intent.getStringExtra("proquan")
        desc.text = intent.getStringExtra("prodesc")
        price.text = intent.getStringExtra("proprice")

        delete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("proId").toString(),
            )
        }

        update.setOnClickListener {
            openupdate(
                        intent.getStringExtra("proId").toString(),
                        intent.getStringExtra("procat").toString(),
                        intent.getStringExtra("proname").toString(),
                        intent.getStringExtra("proquan").toString(),
                        intent.getStringExtra("prodesc").toString(),
                        intent.getStringExtra("proprice").toString()
            )
        }
        }
    private fun deleteRecord(
        id:String
    ){
        val dbref = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val mTask = dbref.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Deleted successfully", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Allproducts::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error->
            Toast.makeText(this,"Error Deleting ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openupdate(
        proId: String,
        procat: String,
        proname: String,
        proquan: String,
        prodesc: String,
        proprice: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_updateproduct, null)

        mDialog.setView(mDialogView)

        val cat1 = mDialogView.findViewById<EditText>(R.id.ucat)
        val name1 = mDialogView.findViewById<EditText>(R.id.uname)
        val qty1 = mDialogView.findViewById<EditText>(R.id.uqty)
        val desc1 = mDialogView.findViewById<EditText>(R.id.udesc)
        val price1 = mDialogView.findViewById<EditText>(R.id.uprice)
        val ubtn1 = mDialogView.findViewById<Button>(R.id.ubtnsubmit)

        cat1.setText(intent.getStringExtra("procat").toString(),)
        name1.setText(intent.getStringExtra("proname").toString(),)
        qty1.setText(intent.getStringExtra("proquan").toString(),)
        desc1.setText(intent.getStringExtra("prodesc").toString(),)
        price1.setText(intent.getStringExtra("proprice").toString(),)

        val alertDialog = mDialog.create()
        alertDialog.show()

        ubtn1.setOnClickListener {
            updateData(
                proId,
                cat1.text.toString(),
                name1.text.toString(),
                qty1.text.toString(),
                desc1.text.toString(),
                price1.text.toString()
            )
            Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()

            cat.text = cat1.text.toString()
            name.text = name1.text.toString()
            quant.text = qty1.text.toString()
            desc.text = desc1.text.toString()
            price.text = price1.text.toString()

            alertDialog.dismiss()
        }
        }

    private fun updateData(
        id:String,
        cat:String,
        name:String,
        qty:String,
        desc:String,
        price:String
    ){
        val dbref = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val proinfo = ProductModel(id,cat,name,qty,desc,price)
        dbref.setValue(proinfo)
    }
    }





