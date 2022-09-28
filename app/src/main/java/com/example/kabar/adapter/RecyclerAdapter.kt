package com.example.kabar.adapter

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kabar.R
import com.example.kabar.model.Articles
import com.example.kabar.model.NewsResponse
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Articles) -> Unit)? = null
    private val diffItemCallback = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles) =
            oldItem.url == newItem.url


        override fun areContentsTheSame(oldItem: Articles, newItem: Articles) =
            oldItem == newItem

    }


    val differ = AsyncListDiffer(this, diffItemCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.default_news_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        val imgView: ImageView = holder.itemView.findViewById(R.id.def_news_img)
        if (data.urlToImage != null) {
            Glide.with(holder.itemView).load(data.urlToImage).into(imgView)
        } else {
            imgView.setImageResource(R.drawable.breaking_news)
        }
        val headText: TextView = holder.itemView.findViewById(R.id.def_news_head_text)
        if (data.description != null) {
            headText.text = data.description
        } else {
            headText.text = holder.itemView.resources.getString(R.string.fake_desc)
        }

        val source: TextView = holder.itemView.findViewById(R.id.def_news_source)
        source.text = data.source.name
        val hour: TextView = holder.itemView.findViewById(R.id.hour_text)
        hour.text = timeFormat(data.publishedAt)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(data)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun timeFormat(publishedAt: String?): String {
        if (publishedAt != null) {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'")
            simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+5")
            var time = 0L
            try {
                time = simpleDateFormat.parse(publishedAt).time
                val now = System.currentTimeMillis()
                val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
            } catch (parseException: ParseException) {
                parseException.printStackTrace()
            }

            val prettyTime = PrettyTime(Locale.getDefault())
            return prettyTime.format(Date(time))
        } else {
            return "1 week ago"
        }

    }

    override fun getItemCount() = differ.currentList.size


    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {

            fun bind() {

            }
        }
    }


}