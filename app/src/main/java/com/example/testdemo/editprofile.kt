package com.example.testdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class editprofile: AppCompatActivity() {
    lateinit var imageView: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editprofile)
        val i=getIntent()
        val phone = i.getStringExtra("phon")
        var nameView=findViewById<EditText>(R.id.editTextTextPersonName2)
        var newName:String=nameView.getText().toString()
        var mailView=findViewById<EditText>(R.id.editTextTextPersonName3)
        var newMail:String=mailView.getText().toString()
        var addressView=findViewById<EditText>(R.id.editTextTextPersonName8)
        var newAddress:String=addressView.getText().toString()
        var phonView=findViewById<EditText>(R.id.editTextTextPersonName81)
        var newPhon:String=phonView.getText().toString()
        var qualificationView=findViewById<EditText>(R.id.editTextPersonQualification)
        var qualif:String=qualificationView.getText().toString()
        var experienceView=findViewById<EditText>(R.id.editTextPersonQualification2)
        var experi:String=experienceView.getText().toString()

        imageView=findViewById<ImageView>(R.id.editpic)
        val btn=findViewById<ImageButton>(R.id.editpicicon)
        btn.setOnClickListener(){
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        var button=findViewById<Button>(R.id.editProfButton)
        button.setOnClickListener(){
            var firebasereference= FirebaseDatabase.getInstance().getReference("Users").child(phone.toString())
            firebasereference.child("name").setValue(newName)
            firebasereference.child("phone").setValue(newPhon)
            firebasereference.child("address").setValue(newAddress)
            firebasereference.child("email").setValue(newMail)
            firebasereference.child("qualification").setValue(qualif)
            firebasereference.child("experience").setValue(experi).addOnSuccessListener {
                Toast.makeText(baseContext, "Successfuly Edited", Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}
