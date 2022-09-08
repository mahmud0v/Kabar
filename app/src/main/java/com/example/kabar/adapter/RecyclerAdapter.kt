package com.example.kabar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kabar.R
import com.example.kabar.model.NewsInfo

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffItemCallback = object : DiffUtil.ItemCallback<NewsInfo>() {
        override fun areItemsTheSame(oldItem: NewsInfo, newItem: NewsInfo) =
            oldItem.headText == newItem.headText

        override fun areContentsTheSame(oldItem: NewsInfo, newItem: NewsInfo) =
            oldItem == newItem
    }


    val differ = AsyncListDiffer(this, diffItemCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.default_news_item,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val data = differ.currentList[position]
        val imgView:ImageView = holder.itemView.findViewById(R.id.def_news_img)
        imgView.setImageResource(data.img)
        val headText:TextView = holder.itemView.findViewById(R.id.def_news_head_text)
        headText.text = holder.itemView.context.getString(data.headText)
        val source:TextView = holder.itemView.findViewById(R.id.def_news_source)
        source.text = data.source
        val hour:TextView = holder.itemView.findViewById(R.id.hour_text)
        hour.text = data.hourAgo

    }


    override fun getItemCount() = differ.currentList.size



    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }



}