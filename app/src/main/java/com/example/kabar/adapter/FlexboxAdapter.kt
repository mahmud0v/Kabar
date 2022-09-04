package com.example.kabar.adapter

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

    private val selectedTopicList = ArrayList<String>()

    var onItemClick:((ArrayList<String>) -> Unit)?=null

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
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(selectedTopicList)
        }
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
                    selectedTopicList.remove(topic)
                    false
                } else {
                    btn.setBackgroundResource(R.drawable.flex_box_item)
                    btn.setTextColor(Color.WHITE)
                    selectedTopicList.add(topic)
                    true
                }
//                Toast.makeText(itemView.context,"${selectedTopicList.size}",Toast.LENGTH_SHORT).show()


            }
        }



    }

    fun getSelectedTopicList() = selectedTopicList
}