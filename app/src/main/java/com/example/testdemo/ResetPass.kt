package com.example.testdemo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class ResetPass : AppCompatActivity() {
    lateinit var mEmail: EditText
    lateinit var mForgetbtn: Button
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pass)

        mEmail= findViewById(R.id.forgetEmail)
        mForgetbtn=findViewById(R.id.button3)
        mAuth= FirebaseAuth.getInstance()

        mForgetbtn.setOnClickListener{
            val email =mEmail.text.toString().trim()
            if(email.isEmpty()){
                return@setOnClickListener

            }
            ForgetPassword(email)
        }
    }
    private fun ForgetPassword(email:String){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task: Task<Void> ->
            if(task.isComplete){
                val intent = Intent(this, Signin::class.java)
                    startActivity(intent)

                Toast.makeText(applicationContext,"Reset Password link is sent to your E-mail", Toast.LENGTH_LONG).show()
            }
        }
    }
}

