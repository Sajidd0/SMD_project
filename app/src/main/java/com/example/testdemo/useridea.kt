package com.example.testdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class useridea: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userpage)
        val i=getIntent()
        val namView=findViewById<TextView>(R.id.nameofuser)
        val mailView=findViewById<TextView>(R.id.emailofuser)
        val contctView=findViewById<TextView>(R.id.contactofuser)
        val addrView=findViewById<TextView>(R.id.addressofuser)
        namView.text=i.getStringExtra("Username").toString().trim()
        mailView.text=i.getStringExtra("mail").toString().trim()
        contctView.text=i.getStringExtra("Contct").toString().trim()
        addrView.text=i.getStringExtra("address").toString().trim()
    }
}