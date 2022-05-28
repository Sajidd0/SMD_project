package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class Home: AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    var titles:List<String> = listOf()
    var contct:List<String> = listOf()
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
       // val tool=findViewById<Toolbar>(R.id.toolbar)
        val chatbtn=toolbar1.findViewById<ImageView>(R.id.chatbtn)
        chatbtn.setOnClickListener(){
            val i=Intent(this,MainActivity::class.java)
            i.putExtra("email",phon)
            startActivity(i);
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

        val frameLayout=findViewById<FrameLayout>(R.id.main_framelayout)
        val drawer=findViewById<DrawerLayout>(R.id.drawerlayout)

        toggle= ActionBarDrawerToggle(this,drawer,toolbar1,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navview.setNavigationItemSelectedListener{
            when(it.itemId) {
                R.id.home->{
                    val i =Intent(this,editprofile::class.java)
                    startActivity(i)
                }
                R.id.Setting->Toast.makeText(applicationContext, "clicked settings1", Toast.LENGTH_SHORT).show()
                R.id.blah->Toast.makeText(applicationContext, "clicked settings2", Toast.LENGTH_SHORT).show()
                R.id.account->Toast.makeText(applicationContext, "clicked settings3", Toast.LENGTH_SHORT).show()
                R.id.signout->Toast.makeText(applicationContext, "clicked settings4", Toast.LENGTH_SHORT).show()

            }
            true
        }
       // val firebasestorage: FirebaseStorage = FirebaseStorage.getInstance()
        //val storagereference = firebasestorage.getReference("Images")
        val firebasereference= FirebaseDatabase.getInstance().getReference("Users")
        titles.toMutableList()
        //val checkQuery: Query = firebasereference.orderByChild("phone")
        firebasereference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(data in snapshot.children){
                    if(data.hasChild("Ideas")){
                        for(idea in data.child("Ideas").children){
                            titles+=(idea.child("title").getValue().toString().trim())
                            contct+=(idea.child("phone").getValue().toString().trim())
                        }
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        layoutManager= LinearLayoutManager(this)
        var recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager= layoutManager
        adapter= RecyclerAdapter(this,titles,contct)
        recyclerView.adapter= adapter
        val btn=findViewById<FloatingActionButton>(R.id.fab)
        btn.setOnClickListener(){
            val intent= Intent(this,addidea::class.java)
            intent.putExtra("contact",phon)
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