package com.example.testdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var SmessageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        var context:Context=this;
        // val intent = Intent()
        val i=getIntent()
        val name = i.getStringExtra("name")
        val receiverUid = i.getStringExtra("email")
        val senderUid= i.getStringExtra("uemail")
        mDbRef = FirebaseDatabase.getInstance().getReference("chats")

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = name

        chatRecyclerView = findViewById(R.id.charRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sentButton)
        SmessageList = ArrayList()
        messageAdapter = MessageAdapter(this, SmessageList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        // logic for adding data to recyclerView
        mDbRef.child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    SmessageList.clear()

                    for(postSnapshot in snapshot.children){

                        val message=postSnapshot.getValue(Message::class.java)
                        SmessageList.add(message!!)

                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        // adding the message to database
        sendButton.setOnClickListener{

            val message = messageBox.text.toString()
            val messageObject = Message(message, senderUid)

            mDbRef.child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    Toast.makeText(context,"wowwwwwwwwhowwwwww",Toast.LENGTH_SHORT).show()
                }
            messageBox.setText("")
        }
    }
}