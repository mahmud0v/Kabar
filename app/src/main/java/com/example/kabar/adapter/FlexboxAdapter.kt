package com.example.kabar.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kabar.R

class FlexboxAdapter : RecyclerView.Adapter<FlexboxAdapter.FlexboxViewHolder>() {



    private val diffItemCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

    }

    val differ = AsyncListDiffer(this, diffItemCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexboxViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flexbox_item, parent, false)
        return FlexboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlexboxViewHolder, position: Int) {
        holder.bind(differ.currentList[position], position)

    }

    override fun getItemCount() =
        differ.currentList.size


    inner class FlexboxViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {


        fun bind(topic: String, position: Int) {
            val btn: AppCompatButton = view.findViewById(R.id.flex_item)
            btn.text = topic
            var clickable = false
            btn.setOnClickListener {
                clickable = if (clickable) {
                    btn.setBackgroundResource(R.drawable.flow_unselect_item)
                    btn.setTextColor(Color.parseColor("#1877F2"))
                    false
                } else {
                    btn.setBackgroundResource(R.drawable.flow_select_item)
                    btn.setTextColor(Color.WHITE)
                    true
                }

            }
        }


    }
}