package com.example.testapp_for_assiment.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp_for_assiment.Adapter.ProductAdapter
import com.example.testapp_for_assiment.Models.ProductModel
import com.example.testapp_for_assiment.R
import com.google.firebase.database.*

class Allproducts : AppCompatActivity() {

    private lateinit var productItem:RecyclerView
    private lateinit var productList:ArrayList<ProductModel>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allproducts)

        productItem=findViewById(R.id.employeeItems)
        productItem.layoutManager=LinearLayoutManager(this,)
        productItem.setHasFixedSize(true)
        productList= arrayListOf<ProductModel>()

        getproductdata()
    }

    private fun getproductdata(){
        productItem.visibility= View.GONE

        dbRef=FirebaseDatabase.getInstance().getReference("Products")

        dbRef.addValueEventListener(object:ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if(snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData=empSnap.getValue(ProductModel::class.java)
                        productList.add(empData!!)
                    }
                    val mAdapter=ProductAdapter(productList)

                    mAdapter.setOnItemClickListener(object:ProductAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@Allproducts, viewproduct::class.java)
                            //put extras
                            intent.putExtra("proId",productList[position].proID)
                            intent.putExtra("proname",productList[position].proName)
                            intent.putExtra("procat",productList[position].proCat)
                            intent.putExtra("prodesc",productList[position].proDesc)
                            intent.putExtra("proquan",productList[position].proQty)
                            intent.putExtra("proprice",productList[position].proprice)
                            startActivity(intent)
                        }
                    })

                    productItem.adapter=mAdapter
                    productItem.visibility=View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }

        })
    }
}
