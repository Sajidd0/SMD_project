package com.example.testdemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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

class Home: AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawer:DrawerLayout
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var titles: List<String> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainhome)
        val toolbar1 = findViewById<MaterialToolbar>(R.id.toolbar)
        var bundle: Bundle? = getIntent().getExtras();
        var nam: String = " "
        var phon: String = " "
        var mail: String = " "
        if (bundle != null) {
            nam = bundle.getString("nam").toString()
            phon = bundle.getString("phon").toString()
            mail = bundle.getString("mail").toString()
        }
        // val tool=findViewById<Toolbar>(R.id.toolbar)
        val chatbtn = toolbar1.findViewById<ImageView>(R.id.chatbtn)
        chatbtn.setOnClickListener() {
            Toast.makeText(baseContext, "wow its a chat", Toast.LENGTH_SHORT).show()
        }
        val navview = findViewById<NavigationView>(R.id.navigation)
        // val navigationView=findViewById<NavigationView>(R.id.navigation)
        val header: View = navview.getHeaderView(0)
        var nameView = header.findViewById<TextView>(R.id.name13)
        var mailView = header.findViewById<TextView>(R.id.email)
        nameView.text = nam
        mailView.text = mail
        //Toast.makeText(baseContext, nam,Toast.LENGTH_SHORT).show()

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar()?.hide();
        //setSupportActionBar(toolbar1);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val frameLayout = findViewById<FrameLayout>(R.id.main_framelayout)
        drawer = findViewById<DrawerLayout>(R.id.drawerlayout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar1, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navview.setNavigationItemSelectedListener(){
                when (it.itemId) {
                    //var id: Int = item.itemI
                    R.id.home -> {
                            val i = Intent(this, editprofile::class.java)
                            startActivity(i)
                        }
                    R.id.Setting -> {
                        Toast.makeText(baseContext, "clicked settings", Toast.LENGTH_SHORT).show()
                    }
                    R.id.blah -> {
                        Toast.makeText(baseContext, "clicked blah", Toast.LENGTH_SHORT).show()
                    }
                    R.id.account -> {
                        Toast.makeText(baseContext, "clicked account", Toast.LENGTH_SHORT).show()
                    }
                    R.id.signout -> {
                        Toast.makeText(baseContext, "clicked signout", Toast.LENGTH_SHORT).show()
                    }

                }
                drawer.closeDrawer(GravityCompat.START)
                true

        }
        /* navview.setNavigationItemSelectedListener{
            var id: Int = it.itemId
            if(id==R.id.home) {
                val i =Intent(this,editprofile::class.java)
                startActivity(i)
            }
            else if(id==R.id.Setting) {
                Toast.makeText(applicationContext, "clicked settings", Toast.LENGTH_SHORT).show()
            }
            else if(id==R.id.blah) {
               Toast.makeText(applicationContext, "clicked blah", Toast.LENGTH_SHORT).show()
            }
            else if(id==R.id.account) {
                Toast.makeText(applicationContext, "clicked account", Toast.LENGTH_SHORT).show()
            }
            else if(id==R.id.signout) {
                Toast.makeText(applicationContext, "clicked signout", Toast.LENGTH_SHORT).show()
            }
            true
            }*/
        val firebasereference = FirebaseDatabase.getInstance().getReference("Users")
        //titles=titles.toMutableList()
        //val checkQuery: Query = firebasereference.orderByChild("phone")
        firebasereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (data in snapshot.children) {
                    if (data.hasChild("Ideas")) {
                        for (idea in data.child("Ideas").children) {
                            titles.toMutableList()
                                .add(idea.child("title").getValue().toString().trim())
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        layoutManager = LinearLayoutManager(this)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        val btn = findViewById<FloatingActionButton>(R.id.fab)
        btn.setOnClickListener() {
            val intent = Intent(this, addidea::class.java)
            intent.putExtra("contact", phon)
            startActivity(intent)
        }

    }

}