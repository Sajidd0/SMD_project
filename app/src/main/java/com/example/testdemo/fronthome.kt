package com.example.testdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class fronthome: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fronthome)
        val btn=findViewById<LottieAnimationView>(R.id.animation_viewbutton)
        btn.setOnClickListener(){
            val intent= Intent(this,Signin::class.java)
            startActivity(intent)
        }
    }
}