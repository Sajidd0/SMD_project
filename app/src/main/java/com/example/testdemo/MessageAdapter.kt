package com.example.testdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val RmessageList: ArrayList<Message>, val senderUid:String):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_RECEIVE = 1
        const val ITEM_SENT = 2
    }
    inner class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var sentMessage =itemView.findViewById<TextView>(R.id.txt_sent_message)
        fun bind(position: Int){
            val recyclerViewmodel=RmessageList[position]
            sentMessage.text=recyclerViewmodel.message
        }

    }

    inner class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var receiveMessage=itemView.findViewById<TextView>(R.id.txt_receive_message)
        fun bind(position: Int){
            val recyclerViewmodel=RmessageList[position]
            receiveMessage.text=recyclerViewmodel.message
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == ITEM_RECEIVE){
            // inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)
            return ReceiveViewHolder(view)
        }else{
            // inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = RmessageList[position]
        //val viewHolder:RecyclerView.ViewHolder
        val temp:String=currentMessage.senderId.toString()

        if(temp.equals(senderUid)){
            // do the stuff for sent view holde
            (holder as SentViewHolder).bind(position)

        }else{
            // do stuff for receive view holder
            //viewHolder = holder as ReceiveViewHolder
            (holder as ReceiveViewHolder).bind(position)
        }

    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = RmessageList[position]
        val temp:String=currentMessage.senderId.toString()
        if(temp.equals(senderUid)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return RmessageList.size

    }

}