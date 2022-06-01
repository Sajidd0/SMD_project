package com.example.testdemo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testdemo.User
import com.google.firebase.auth.FirebaseAuth


class UserAdapter(var context: Context, val userList: ArrayList<User>, val uemail:String):

    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    lateinit var currentUser:User
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // bind with text
        currentUser = userList[position]
        holder.textName.text = userList[position].name

       /* holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)
            val uemail=this.uemail
            intent.putExtra("name",currentUser.name)
            intent.putExtra("email",currentUser.email)
            intent.putExtra("uemail", uemail)
            context.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName:TextView
        init{
            context=itemView.context
            textName= itemView.findViewById<TextView>(R.id.username_txt)
            itemView.setOnClickListener(){
                var name:String= textName.text.toString()
                var receivernum:String=name
                var receivername:String=name
                for(obj in userList)
                {
                    if(obj.name.equals(name)) {
                        receivername = obj.name.toString()
                        receivernum = obj.phone.toString()
                        break
                    }
                }
                val sendernum:String= uemail

                val intent = Intent(context,ChatActivity::class.java)
                intent.putExtra("name",receivername)
                intent.putExtra("email", receivernum)
                intent.putExtra("uemail", sendernum)
                print(currentUser.name)
                print(currentUser.phone)
               // Toast.makeText(context,currentUser.email+uemail,Toast.LENGTH_SHORT).show()
                context.startActivity(intent)
            }
        }


    }
}