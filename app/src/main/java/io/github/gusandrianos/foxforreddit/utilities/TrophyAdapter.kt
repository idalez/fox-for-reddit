package io.github.gusandrianos.foxforreddit.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.gusandrianos.foxforreddit.R
import io.github.gusandrianos.foxforreddit.data.models.Thing
import io.github.gusandrianos.foxforreddit.utilities.TrophyAdapter.ViewHolder

class TrophyAdapter(private val trophies: List<Thing>) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var trophyImg = view.findViewById(R.id.img_trophy_item_icon) as ImageView
        var trophyTitle = view.findViewById(R.id.txt_trophy_item_title) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trophies_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.trophyTitle.text = trophies[position].data!!.name
        Glide.with(holder.itemView).load(trophies[position].data!!.icon70).into(holder.trophyImg)
    }

    override fun getItemCount(): Int {
        return trophies.size
    }
}