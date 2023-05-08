package com.example.normalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class DriverAccountActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var usersArrayList: ArrayList<DPaymentList>
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_account)

        recyclerView = findViewById(R.id.driverAccount)
         recyclerView.layoutManager = LinearLayoutManager(this)

        usersArrayList = arrayListOf()

        database = FirebaseDatabase.getInstance().getReference("DriverPayments")
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(DPaymentList::class.java)
                        if(!usersArrayList.contains(user)){
                            usersArrayList.add(user!!)
                        }
                    }
                    recyclerView.adapter= DriverAdpterPayment(usersArrayList)



                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DriverAccountActivity,error.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}