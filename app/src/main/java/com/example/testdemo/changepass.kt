package com.example.testdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class changepass:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepass)
        var phonView=findViewById<TextInputEditText>(R.id.phon)

        var passView=findViewById<TextInputEditText>(R.id.newpasswordstate)

        var conPassView=findViewById<TextInputEditText>(R.id.confirmpasswordstate)

        var button=findViewById<Button>(R.id.button4)
        button.setOnClickListener {
            var phon:String=phonView.getText().toString()
            var pass:String=passView.getText().toString()
            var conPass:String=conPassView.getText().toString()
            if(pass==conPass){
                var firebasereference = FirebaseDatabase.getInstance().getReference("Users")
                firebasereference.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChild(phon)){
                            firebasereference.child(phon).child("password").setValue(pass)
                            firebasereference.child(phon).child("conPassword").setValue(pass)
                            Toast.makeText(baseContext, "Successfully Updated", Toast.LENGTH_SHORT).show()
                        }else
                        {
                            Toast.makeText(baseContext, "Incorrect Phone Number", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
            }else
            {
                Toast.makeText(applicationContext,"Password don't match",Toast.LENGTH_LONG).show()
            }
        }

    }
}