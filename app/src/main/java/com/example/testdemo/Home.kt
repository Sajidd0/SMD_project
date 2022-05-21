package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class Home: AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainhome)
        val toolbar1=findViewById<MaterialToolbar>(R.id.toolbar)
        var bundle: Bundle? =getIntent().getExtras();
        var nam:String=" "
        var phon:String=" "
        var mail:String=" "
        if(bundle!=null)
        {
            nam=bundle.getString("nam").toString()
            phon= bundle.getString("phon").toString()
            mail= bundle.getString("mail").toString()
        }
        val navview=findViewById<NavigationView>(R.id.navigation)
       // val navigationView=findViewById<NavigationView>(R.id.navigation)
        val header:View=navview.getHeaderView(0)
        var nameView=header.findViewById<TextView>(R.id.name13)
        var mailView=header.findViewById<TextView>(R.id.email)
        nameView.text = nam
        mailView.text=mail
        //Toast.makeText(baseContext, nam,Toast.LENGTH_SHORT).show()

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar()?.hide();
        //setSupportActionBar(toolbar1);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val frameLayout=findViewById<FrameLayout>(R.id.main_framelayout)
        val drawer=findViewById<DrawerLayout>(R.id.drawerlayout)

        toggle= ActionBarDrawerToggle(this,drawer,toolbar1,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> Toast.makeText(applicationContext,"clicked Home",Toast.LENGTH_SHORT).show()
                R.id.Setting->Toast.makeText(applicationContext,"clicked settings",Toast.LENGTH_SHORT).show()
                R.id.blah->Toast.makeText(applicationContext,"clicked blah",Toast.LENGTH_SHORT).show()
                R.id.account->Toast.makeText(applicationContext,"clicked account",Toast.LENGTH_SHORT).show()
                R.id.signout->Toast.makeText(applicationContext,"clicked signout",Toast.LENGTH_SHORT).show()

            }
            true
        }
        layoutManager= LinearLayoutManager(this)
        var recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager= layoutManager
        adapter= RecyclerAdapter()
        recyclerView.adapter= adapter
        val btn=findViewById<FloatingActionButton>(R.id.fab)
        btn.setOnClickListener(){
            val intent= Intent(this,addidea::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}