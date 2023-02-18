package com.example.kabar.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Kabar")
@Parcelize
data class Articles(
   @PrimaryKey(autoGenerate = true)
   val id:Int,
   val source:Source,
   val author:String?,
   val title:String?,
   val description:String?,
   val url:String?,
   val urlToImage:String?,
   val publishedAt:String?,
   val content:String?,
   var isLike:Boolean = false,
   var isBookmarked:Boolean = false
) : Parcelable