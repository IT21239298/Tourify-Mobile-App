package com.example.testapp_for_assiment.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.testapp_for_assiment.R

class MainActivity : AppCompatActivity() {
    private lateinit var btnInsertData:Button
    private lateinit var btnFetchData:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsertData=findViewById(R.id.add)
        btnFetchData=findViewById(R.id.fetch)

        btnInsertData.setOnClickListener{
            val intent=Intent(this, addproduct::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener{
            val fetchData=Intent(this, Allproducts::class.java)
            startActivity(fetchData)
        }

    }
}