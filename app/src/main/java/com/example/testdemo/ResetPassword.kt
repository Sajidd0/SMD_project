package com.example.testdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task


import com.google.firebase.auth.FirebaseAuth

lateinit var mEmail: EditText
lateinit var mforgetbtn: Button

lateinit var mAuth : FirebaseAuth


class ResetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        mEmail=findViewById(R.id.forgetEmail)
        mforgetbtn=findViewById(R.id.button3)
        mAuth=FirebaseAuth.getInstance()

        mforgetbtn.setOnClickListener{
            val email= mEmail.text.toString().trim()
            if(email.isEmpty()){
                mEmail.error="Enter E-mail"
                return@setOnClickListener
            }

            forgetPassword(email)
        }
    }

    private fun forgetPassword(email:String) {
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener{ task: Task<Void> ->
            if(task.isComplete){
                val i = Intent(this, Signin::class.java)

                startActivity(i)

                Toast.makeText( applicationContext,"Reset password link in sent", Toast.LENGTH_LONG).show()
            }
        }


    }
}

private fun <TResult> Task<TResult>.addOnSuccessListener(function: (Task<Void>) -> Unit) {
    TODO("Not yet implemented")
}



