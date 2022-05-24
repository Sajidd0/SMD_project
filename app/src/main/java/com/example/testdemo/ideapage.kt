package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.ViewPager

class ideapage: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ideapage)
        val obj=this
        val name=findViewById<TextView>(R.id.nameofentr)
        name.setOnClickListener(){
            val i=Intent(this,useridea::class.java)
            startActivity(i)
        }
        val textbtn = findViewById<Button>(R.id.txtbtn)
        textbtn.setOnClickListener {
            val i = Intent(this, ChatActivity::class.java)
            startActivity(i);
        }
        val btn=findViewById<Button>(R.id.investit)
        btn.setOnClickListener(){
        val diaglogbuilder =AlertDialog.Builder(this)
        //val layout:LayoutInflater= getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater;
        val popup: View =layoutInflater.inflate(R.layout.investment,null)
        val text1=popup.findViewById<TextView>(R.id.textView13)
        val text2=popup.findViewById<TextView>(R.id.investment)
        val btn1=popup.findViewById<Button>(R.id.plus)
        val btn2=popup.findViewById<Button>(R.id.minus)
        val btn3=popup.findViewById<Button>(R.id.cancel)
        val btn4=popup.findViewById<Button>(R.id.done)
        diaglogbuilder.setView(popup)
        val dialog=diaglogbuilder.create()
        dialog.show()
        btn3.setOnClickListener(){
            dialog.dismiss()
        }
            btn1.setOnClickListener(){
                val temp=text2.text.toString()
                val temp1:Int=temp.toInt()+1000
                text2.setText(temp1.toString())
            }
            btn2.setOnClickListener(){
                val temp=text2.text.toString()
                val temp1:Int=temp.toInt()-1000
                text2.setText(temp1.toString())
            }

        }

    }

}