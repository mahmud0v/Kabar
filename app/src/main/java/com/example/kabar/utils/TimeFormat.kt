package com.example.kabar.utils

import android.text.format.DateUtils
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeFormat {


    fun getTimeFormat(publishedAt: String?): String {
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
}
