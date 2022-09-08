package com.example.kabar.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFormat {

    companion object{
        @SuppressLint("SimpleDateFormat")
        private fun timeFormat(publishedAt: String) {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'")
            simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+5")
            var time=0L
            try {
                time = simpleDateFormat.parse(publishedAt).time
                val now = System.currentTimeMillis()
                val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
            } catch (parseException: ParseException) {
                parseException.printStackTrace()
            }

            val prettyTime = PrettyTime(Locale.getDefault())
            val ago = prettyTime.format(Date(time))

        }
    }
}