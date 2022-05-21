package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
class Signup:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val firebasereference=FirebaseDatabase.getInstance().getReference("Users")

        super.onCreate(savedInstanceState)



        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar()?.hide();
        setContentView(R.layout._signup)
        val nameView = findViewById<EditText>(R.id.editTextTextPersonName2)
        val emailView = findViewById<EditText>(R.id.editTextTextPersonName3)
        val PassView = findViewById<EditText>(R.id.editTextTextPassword2)
        val confPassView = findViewById<EditText>(R.id.editTextTextPassword3)
        val addrView = findViewById<EditText>(R.id.editTextTextPersonName8)
        val mobView = findViewById<EditText>(R.id.editTextTextPersonName81)


        val signUpButton = findViewById<ImageButton>(R.id.button2)
        signUpButton.setOnClickListener {
            val name=nameView.getText().toString().trim()
            val email = emailView.getText().toString().trim()
            val password = PassView.getText().toString().trim()
            val conPass = confPassView.getText().toString().trim()
            val addr=addrView.getText().toString().trim()
            val mob=mobView.getText().toString().trim()
            val user=UserHelper(name,email,password,conPass,addr,mob)
            firebasereference.child(mob).setValue(user)
        }
    }
}