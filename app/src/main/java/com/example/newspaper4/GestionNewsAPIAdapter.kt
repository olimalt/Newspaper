package com.example.newspaper4

import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newspaper4.GestionNewsAPIAdapter.ListHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.articles_recyclerview.view.*
import org.w3c.dom.Text
import java.net.URI
import java.util.logging.Level.parse

class GestionNewsAPIAdapter (private var gestionewsapi: List<Article>?) :
    RecyclerView.Adapter<ListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.articles_recyclerview, parent, false)
        return ListHolder(v)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        //gestionewsapi?.let { (holder as ListHolder).bind(it.get(position)) }
        val currentItem = gestionewsapi?.get(position)
        if (currentItem != null) {
            holder.imageView.setImageURI(currentItem.urlToImage?.toUri())
            holder.description.text = currentItem.description
            holder.date.text = currentItem.publishedAt
        }

    }

    override fun getItemCount(): Int {return gestionewsapi!!.size
    }

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.itemImage
        val description : TextView = itemView.itemDescription
        val date :TextView = itemView.itemDate
        //fun bind(_list: Article) {

            //itemView.itemDescription.text = _list.description
            //itemView.itemDate.text=_list.publishedAt

        //}
    }
}