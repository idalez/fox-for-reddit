package io.github.gusandrianos.foxforreddit.utilities

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.gusandrianos.foxforreddit.R
import io.github.gusandrianos.foxforreddit.data.models.Thing

class MessagesWithUserAdapter(private val thing: Thing) : RecyclerView.Adapter<MessagesWithUserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUser: TextView = view.findViewById(R.id.txt_messages_with_user_item_username)
        val txtTimeSent: TextView = view.findViewById(R.id.txt_messages_with_user_item_time_sent)
        val txtBody: TextView = view.findViewById(R.id.txt_messages_with_user_item_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.messages_with_user_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = thing.data?.children!![position].data!!
        holder.txtUser.text = data.author
        holder.txtTimeSent.text = DateUtils.getRelativeTimeSpanString(data.createdUtc.times(1000)).toString()
        holder.txtBody.text = data.body
    }

    override fun getItemCount(): Int {
       return thing.data?.children?.size!!
    }
}