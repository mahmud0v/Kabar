package com.example.kabar.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.kabar.model.Articles
import com.example.kabar.utils.Converts


@Database(
    entities = [Articles::class],
    version = 1
)
@TypeConverters(Converts::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsDao() : NewsDao


}