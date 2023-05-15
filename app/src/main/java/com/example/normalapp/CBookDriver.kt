package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CBookDriver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cbook_driver)

        val backButton: ImageView = findViewById(R.id.imageView5)
        backButton.setOnClickListener {
            // Start the main activity
            val intent = Intent(this, CMainActivity::class.java)
            startActivity(intent)
        }
    }
}