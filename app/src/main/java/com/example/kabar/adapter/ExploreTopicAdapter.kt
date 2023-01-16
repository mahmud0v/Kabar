package com.example.kabar.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kabar.R
import com.example.kabar.model.ExploreTopic
import com.example.kabar.model.ExploreTopicEntity

class ExploreTopicAdapter : RecyclerView.Adapter<ExploreTopicAdapter.ExploreViewHolder>() {

    var onItemClick: ((ExploreTopic) -> Unit)? = null
    private val diffItemCallback = object : DiffUtil.ItemCallback<ExploreTopic>() {
        override fun areItemsTheSame(oldItem: ExploreTopic, newItem: ExploreTopic) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: ExploreTopic, newItem: ExploreTopic) =
            oldItem == newItem

    }

    val differ = AsyncListDiffer(this, diffItemCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_topic_item, parent, false)
        return ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {

        holder.bind(position)

    }

    override fun getItemCount() = differ.currentList.size


    inner class ExploreViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.exp_top_img_id)
        private val title: TextView = view.findViewById(R.id.title_txt)
        private val desc: TextView = view.findViewById(R.id.desc_txt)
        private val button: AppCompatButton = view.findViewById(R.id.btn_id)
        fun bind(position: Int) {
            image.setImageResource(differ.currentList[position].imgRes)
            title.text = view.context.getString(differ.currentList[position].title)
            desc.text = view.context.getString(differ.currentList[position].desc)
            if (differ.currentList[position].click) {
                savedButton()
            } else {
                saveButton()
            }
            button.setOnClickListener {
                if (differ.currentList[position].click) {
                    saveButton()
                    differ.currentList[position].click = false
                } else {
                    savedButton()
                    differ.currentList[position].click = true
                }
                onItemClick?.invoke(differ.currentList[position])
            }

        }

        private fun savedButton() {
            button.setBackgroundResource(R.drawable.flex_box_item)
            button.setTextColor(Color.WHITE)
            button.text = view.context.getString(R.string.saved)
        }

        private fun saveButton() {
            button.setBackgroundResource(R.drawable.flow_unselect_item)
            button.setTextColor(Color.parseColor("#1877F2"))
            button.text = view.context.getString(R.string.save)
        }
    }
}