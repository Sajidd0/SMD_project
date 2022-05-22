package com.example.testdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class addidea:AppCompatActivity() {
    lateinit var imageView: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addidea)
        val spinner: Spinner = findViewById(R.id.spinner)
        val phone:String?=getIntent().getStringExtra("contact").toString()
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.status_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            imageView=findViewById<ImageView>(R.id.image)
            val btn=findViewById<ImageButton>(R.id.addideaim)
            btn.setOnClickListener(){
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
            val titleView=findViewById<EditText>(R.id.title)
            val discriptionView=findViewById<EditText>(R.id.description)
            val contactView=findViewById<EditText>(R.id.contactinidea)
            val invstamntView=findViewById<EditText>(R.id.amountinves)
            val buyamntView=findViewById<EditText>(R.id.buyamount)
            val button=findViewById<Button>(R.id.addideabtn)
            button.setOnClickListener(){
                val title:String=titleView.getText().toString().trim()
                val description:String=discriptionView.getText().toString().trim()
                val contact:String=contactView.getText().toString().trim()
                val invstamnt:String=invstamntView.getText().toString().trim()
                val buyamnt:String=buyamntView.getText().toString().trim()
                val decsObject:addIdeaHelper=addIdeaHelper(title,description,contact,invstamnt,buyamnt)
                val firebasereference= FirebaseDatabase.getInstance().getReference("Users")
                firebasereference.child(phone.toString()).child("Ideas").child(title).setValue(decsObject)
                Toast.makeText(baseContext, "Idea successfully uploaded",Toast.LENGTH_SHORT).show()


            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}