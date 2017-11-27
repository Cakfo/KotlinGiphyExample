package com.spaja.kotlinexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.spaja.kotlinexample.model.GifData

/**
 * Created by Spaja on 27-Nov-17.
 */
class GifsRecyclerAdapter(private val mDataSet: ArrayList<GifData>) : RecyclerView.Adapter<GifsRecyclerAdapter.GifsViewHolder>() {


    override fun onBindViewHolder(holder: GifsViewHolder?, position: Int) {
        holder?.title?.text = mDataSet[position].title
        Glide.with(holder?.picture?.context).asGif().load(mDataSet[position].images.fixedWidth.url).into(holder?.picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GifsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return GifsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    class GifsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.gif_title)
        val picture: ImageView = itemView.findViewById(R.id.gif_picture)
    }
}