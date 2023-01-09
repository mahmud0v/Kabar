package com.example.kabar.utils

import androidx.room.TypeConverter
import com.example.kabar.model.Source

class Converts {

    @TypeConverter
    fun sourceToString(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun fromSource(name:String) : Source{
        return Source(name = name)
    }


}