package com.example.testdemo

import android.app.DownloadManager
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
import com.google.firebase.ktx.Firebase

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
                        val nameFromDB:String=snapshot.child(emailstr).child("name").getValue().toString()
                        val phoneFromDB:String=snapshot.child(emailstr).child("phone").getValue().toString()
                        val emailFromDB:String=snapshot.child(emailstr).child("email").getValue().toString()
                        intnt.putExtra("nam",nameFromDB)
                        intnt.putExtra("phon", phoneFromDB)
                        intnt.putExtra("mail",emailFromDB)
                        startActivity(intnt)
                        //Toast.makeText(baseContext, nameFromDB,Toast.LENGTH_SHORT).show()


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            /* Auth.signInWithEmailAndPassword(emailstr, passstr)
                 .addOnCompleteListener(this) { task ->
                     if (task.isSuccessful) {
                         startActivity(Intent(this,Home::class.java))
                         finish()

                     } else {
                         // If sign in fails, display a message to the user.
                         Toast.makeText(baseContext, "Authentication failed.",
                             Toast.LENGTH_SHORT).show()
                     }
                 }
                 */

        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        /*val currentUser = Auth.currentUser
        if(currentUser != null){
            reload(currentUser);
        }*/
    }

}