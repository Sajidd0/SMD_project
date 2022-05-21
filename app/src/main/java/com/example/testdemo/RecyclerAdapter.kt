package com.example.testdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var titles= arrayOf("Earthquake proof office chairs Predictive horns for cars Twitch.tv for putting together Ikea furniture Wearable fog machines Bubble prediction algorithms",
        "I want to start a mobile school that visits such villages around the year, camps there for a few months & we teach both children & grown ups",
        "Turning prisons into greenhouses, utilizing low-risk prisoners as groundskeepers and gardeners, simultaneously educating them to become chefs as rehabilitation into poverty-heavy cities",
        "Skill-Swap' is a marketplace where people trade services with one another to help achieve their goals. 'I'll build your website if you do my accounting",
        "Rent-A-Swag is a service where I rent out high-end clothing to teens, tweens, and everything-in-between",
        "A try factory... somewhere you can try different professions without degrees, in real world experiences to see if it fits your life before committing to study/expensive training etc",
        "New type of cemetery that replaces tombstones with trees to lower funeral costs, give back to the environment, and bring new life to death",
        "My idea is for a veterinary app to speak directly to professionals for any issue. And connect the individual with a vet in their area",
        "An all natural, low sodium, low sugar, anti-inflammatory sports tea. Husband/Wife team with over 15 years professional business experience")
    private var likecount= arrayOf("100", "200", "250", "299", "199", "107", "765", "676", "322")
    private val itemImages= intArrayOf(
        R.drawable.idea1,
        R.drawable.idea2,
        R.drawable.idea3,
        R.drawable.idea4,
        R.drawable.idea5,
        R.drawable.idea6,
        R.drawable.idea7,
        R.drawable.idea8,
        R.drawable.idea9
    )
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemtitle.text= titles[position]
        holder.likecount.text= likecount[position]
        holder.itemImage.setImageResource(itemImages[position])

    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        var itemImage: ImageView
        var itemtitle: TextView
        var likecount: TextView

        init{
            itemImage= itemview.findViewById(R.id.item_image)
            itemtitle= itemview.findViewById(R.id.item_title)
            likecount= itemview.findViewById(R.id.item_count)


        }
    }


}
