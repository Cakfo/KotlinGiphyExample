package com.spaja.kotlinexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.spaja.kotlinexample.model.GifData
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Spaja on 27-Nov-17.
 */
class GifsRecyclerAdapter(private val mDataSet: ArrayList<GifData>) : RecyclerView.Adapter<GifsRecyclerAdapter.GifsViewHolder>() {


    override fun onBindViewHolder(holder: GifsViewHolder?, position: Int) {
        holder?.itemView?.gifTitle?.text = mDataSet[position].title
        Glide.with(holder?.itemView?.gifPicture?.context)
                .asGif()
                .load(mDataSet[position].images.fixedWidth.url)
                .into(holder?.itemView?.gifPicture)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GifsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return GifsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    class GifsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}