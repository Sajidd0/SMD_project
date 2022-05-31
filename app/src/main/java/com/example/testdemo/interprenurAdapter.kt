package com.example.testdemo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class interprenurAdapter(var Cont:Context,var phone:String): RecyclerView.Adapter<interprenurAdapter.ViewHolder>() {
    var titles:List<String> = listOf()
    //private lateinit var context:Context;
    var contct:List<String> = listOf()
    val firebasestorage: FirebaseStorage = FirebaseStorage.getInstance()
    val storagereference = firebasestorage.getReference("Images")
    private var likecount= arrayOf("100", "200", "250", "299", "199", "107", "765", "676", "322")
    private val itemImages= intArrayOf(
        R.drawable.idea1,
        R.drawable.idea2,
        R.drawable.idea3,
        R.drawable.idea4,
        R.drawable.idea5,
        R.drawable.idea6,
        R.drawable.idea7,
        R.drawable.idea8,
        R.drawable.idea9
    )
    /*init{
        }*/
    init {
        val firebasereference= FirebaseDatabase.getInstance().getReference("Users").child(phone).child("Ideas")
        titles.toMutableList()


        //val checkQuery: Query = firebasereference.orderByChild("phone")
        firebasereference.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(data in snapshot.children){
                    titles+=(data.child("title").getValue().toString().trim())
                    contct+=(data.child("phone").getValue().toString().trim())
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        //titles+=("dsakf;lkj")

    }
    override fun onCreateViewHolder(parent:ViewGroup,viewtype:Int):ViewHolder{
        val v= LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: interprenurAdapter.ViewHolder, position: Int) {
        holder.itemtitle.text= titles[position]
        holder.likecount.text= likecount[position]
        // holder.itemImage.setImageResource(itemImages[position])
        storagereference.child(contct[position]).child(holder.itemtitle.text.toString()).downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(holder.itemImage)
        }

    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        var itemImage: ImageView
        var itemtitle: TextView
        var likecount: TextView


        init{
            Cont=itemview.context
            itemImage= itemview.findViewById(R.id.item_image)
            itemtitle= itemview.findViewById(R.id.item_title)
            likecount= itemview.findViewById(R.id.item_count)
            itemview.setOnClickListener(){
                val titleName:String=itemtitle.text.toString()
                val i=Intent(Cont,ideapage::class.java)
                i.putExtra("title12",titleName)
                Cont.startActivity(i)
            }

        }
    }
}