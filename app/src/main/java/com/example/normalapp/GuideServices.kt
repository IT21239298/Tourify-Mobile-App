package com.example.normalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class GuideServices : AppCompatActivity() {

    private lateinit var recyclerViewG: RecyclerView
    private lateinit var guidesArrayList: ArrayList<TourTypes>
    private lateinit var btnAddNew:Button
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_services)

        recyclerViewG =findViewById(R.id.guideView)
        recyclerViewG.layoutManager=LinearLayoutManager(this)

        guidesArrayList = arrayListOf()


        btnAddNew=findViewById(R.id.newAddbttn)
      btnAddNew.setOnClickListener {
          val intent = Intent(this,guideAddPackages::class.java)
           startActivity(intent)
       }
        database = FirebaseDatabase.getInstance().getReference("GuideAddPackages")
        database.addValueEventListener(object :ValueEventListener{




            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(dataSnapShot in snapshot.children){
                        val guide = dataSnapShot.getValue(TourTypes :: class.java)
                        if(!guidesArrayList.contains(guide)){
                            guidesArrayList.add(guide!!)
                        }
                    }
                    recyclerViewG.adapter = GuideAdapter(guidesArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@GuideServices ,error.toString(),Toast.LENGTH_SHORT).show()

            }

        })


    }


}