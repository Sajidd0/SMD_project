package com.example.testdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class Signin : AppCompatActivity() {
    val cont: Context = this
    var a=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val chip=findViewById<ImageButton>(R.id.imageButton)
        val password=findViewById<EditText>(R.id.editTextTextPassword)
        val Signin_button=findViewById<ImageButton>(R.id.button2)
        val emailview=findViewById<EditText>(R.id.editTextTextEmailAddress)
        val frgtbtn =findViewById<TextView>(R.id.forgetbtn)
        chip.setOnClickListener {
            if(a==false)
            {
                chip.setBackgroundResource(R.drawable.hide)
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                a=true
            }
            else
            {
                chip.setBackgroundResource(R.drawable.show)
                password.setTransformationMethod(PasswordTransformationMethod.getInstance())
                a=false
            }
        }

        frgtbtn.setOnClickListener{
            val i = Intent(applicationContext ,ResetPass::class.java)
            startActivity(i)
        }
        val signUpbtn=findViewById<TextView>(R.id.button)
        signUpbtn.setOnClickListener {
            val i= Intent(this,Signup::class.java);
            startActivity(i);
        }
        Signin_button.setOnClickListener{
            val emailstr=emailview.getText().toString().trim()
            val passstr=password.getText().toString().trim()
            val firebase=FirebaseDatabase.getInstance()
            val firebasereference=firebase.getReference("Users")
            val intnt=Intent(this,Home::class.java)
            val checkQuery:Query = firebasereference.orderByChild("phone").equalTo(emailstr)
            checkQuery.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val passFromDB:String=snapshot.child(emailstr).child("password").getValue().toString()
                        if(passstr==passFromDB) {
                            val nameFromDB: String =
                                snapshot.child(emailstr).child("name").getValue().toString()
                            val phoneFromDB: String =
                                snapshot.child(emailstr).child("phone").getValue().toString()
                            val emailFromDB: String =
                                snapshot.child(emailstr).child("email").getValue().toString()
                            val statusFromDB:String=snapshot.child(emailstr).child("status").getValue().toString()
                            intnt.putExtra("nam", nameFromDB)
                            intnt.putExtra("phon", phoneFromDB)
                            intnt.putExtra("mail", emailFromDB)
                            intnt.putExtra("status", statusFromDB)
                            startActivity(intnt)
                            //Toast.makeText(baseContext, nameFromDB,Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(baseContext, "Incorrect Password",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(baseContext, "Sign Up First",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

}