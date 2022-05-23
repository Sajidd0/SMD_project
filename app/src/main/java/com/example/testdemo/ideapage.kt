package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ideapage: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ideapage)

        val textbtn = findViewById<Button>(R.id.txtbtn)
        textbtn.setOnClickListener{
            val i=Intent(this, ChatActivity::class.java)
            startActivity(i);
        }

    }

}