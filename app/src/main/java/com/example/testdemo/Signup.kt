package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.database.FirebaseDatabase
class Signup:AppCompatActivity() {
    lateinit var status:String
    override fun onCreate(savedInstanceState: Bundle?) {
        val firebasereference=FirebaseDatabase.getInstance().getReference("Users")

        super.onCreate(savedInstanceState)


        val anime=findViewById<LottieAnimationView>(R.id.loadinganime)
        anime.visibility= View.GONE
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar()?.hide();
        setContentView(R.layout._signup)
        val nameView = findViewById<EditText>(R.id.editTextTextPersonName2)
        val emailView = findViewById<EditText>(R.id.editTextTextPersonName3)
        val PassView = findViewById<EditText>(R.id.editTextTextPassword2)
        val confPassView = findViewById<EditText>(R.id.editTextTextPassword3)
        val addrView = findViewById<EditText>(R.id.editTextTextPersonName8)
        val mobView = findViewById<EditText>(R.id.editTextTextPersonName81)
        val sw1 = findViewById<Switch>(R.id.switch1)
        sw1?.setOnCheckedChangeListener({ _ , isChecked ->
             if (isChecked) status="true" else status="false"
        })
        val signUpButton = findViewById<ImageButton>(R.id.button2)
        signUpButton.setOnClickListener {
            anime.visibility=View.VISIBLE
            val name=nameView.getText().toString().trim()
            val email = emailView.getText().toString().trim()
            val password = PassView.getText().toString().trim()
            val conPass = confPassView.getText().toString().trim()
            val addr=addrView.getText().toString().trim()
            val mob=mobView.getText().toString().trim()

            val user:UserHelper=UserHelper(name,email,password,conPass,addr,mob,status)
            if(user.checkCredentials()){
                firebasereference.child(mob).setValue(user)
                Toast.makeText(baseContext, "Successfuly SignUp", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(baseContext, "Incorrect Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}