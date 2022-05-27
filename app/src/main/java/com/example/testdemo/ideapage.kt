package com.example.testdemo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.File
import java.util.*

class ideapage: AppCompatActivity(){
    var username:String=""
    var investmrntamount:String=""
    var BuyAmount:String=""
    var email:String=""
    var contct:String=""
    var addr:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ideapage)
        val obj=this
        val i:Intent=getIntent()
        val titleName=i.getStringExtra("title12")
        val descTextView=findViewById<TextView>(R.id.textView9)
        val ideaImageView=findViewById<ImageView>(R.id.ideaimag)
        //val firebasestorage: FirebaseStorage = FirebaseStorage.getInstance()
        //val storagereference = firebasestorage.getReference("Images")
        val firebaseref=FirebaseDatabase.getInstance().getReference("Users")
        firebaseref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(data in snapshot.children){
                    if(data.hasChild("Ideas")){
                        for(idea in data.child("Ideas").children){
                            if(idea.child("title").getValue().toString().trim()==titleName){
                                descTextView.text = idea.child("desc").getValue().toString().trim()
                                investmrntamount = idea.child("invstamnt").getValue().toString().trim()
                                BuyAmount = idea.child("buyamnt").getValue().toString().trim()
                                username=data.child("name").getValue().toString().trim()
                                email=data.child("email").getValue().toString().trim()
                                contct=data.child("phone").getValue().toString().trim()
                                addr=data.child("address").getValue().toString().trim()
                                Picasso.get().load(data.child("image").getValue().toString().trim()).into(ideaImageView)

                            }
                        }
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        /*var imageuri:Uri
        val mountainsRef = storagereference.child(contct)
        val localfile:File= File.createTempFile(titleName,".jpg")

        mountainsRef.getFile(localfile)
        val bitmap: Bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath())
        ideaImageView.setImageBitmap(bitmap)*/
        val name=findViewById<TextView>(R.id.nameofentr)
        name.text=titleName.toString()
        name.setOnClickListener(){
            val i=Intent(this,useridea::class.java)
            i.putExtra("Username",username)
            i.putExtra("mail",email)
            i.putExtra("Contct",contct)
            i.putExtra("address",addr)
            startActivity(i)
        }
        val textbtn = findViewById<Button>(R.id.txtbtn)
        textbtn.setOnClickListener {
            val i = Intent(this, ChatActivity::class.java)
            startActivity(i);
        }
        val btn=findViewById<Button>(R.id.investit)
        btn.setOnClickListener(){
        val diaglogbuilder =AlertDialog.Builder(this)
        //val layout:LayoutInflater= getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater;
        val popup: View =layoutInflater.inflate(R.layout.investment,null)
        val text1=popup.findViewById<TextView>(R.id.textView13)
        val text2=popup.findViewById<TextView>(R.id.investment)
        val btn1=popup.findViewById<Button>(R.id.plus)
        val btn2=popup.findViewById<Button>(R.id.minus)
        val btn3=popup.findViewById<Button>(R.id.cancel)
        val btn4=popup.findViewById<Button>(R.id.done)
        diaglogbuilder.setView(popup)
        val dialog=diaglogbuilder.create()
        dialog.show()
        btn3.setOnClickListener(){
            dialog.dismiss()
        }
            btn1.setOnClickListener(){
                val temp=text2.text.toString()
                val temp1:Int=temp.toInt()+1000
                text2.setText(temp1.toString())
            }
            btn2.setOnClickListener(){
                val temp=text2.text.toString()
                val temp1:Int=temp.toInt()-1000
                text2.setText(temp1.toString())
            }

        }

    }

}