package com.example.testdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class idea: AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    override fun onCreate(savedInstances: Bundle?)
    {
        super.onCreate(savedInstances)
        setContentView(R.layout.ideaactivity)
        layoutManager= LinearLayoutManager(this)
        var recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager= layoutManager
        adapter= RecyclerAdapter()
        recyclerView.adapter= adapter


    }


}