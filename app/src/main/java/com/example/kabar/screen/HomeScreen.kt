package com.example.kabar.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.databinding.HomeScreenBinding
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeScreen : Fragment(R.layout.home_screen) {
    private val binding: HomeScreenBinding by viewBinding()

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     timeFormat2("2022-09-05T09:14:55Z")
    }


    private fun timeFormat1(publishedAt: String) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'")
        val date = simpleDateFormat.parse(publishedAt)
        val niceDate = DateUtils.getRelativeTimeSpanString(
            date.time,
            Calendar.getInstance().timeInMillis,
            DateUtils.MINUTE_IN_MILLIS
        )
        Toast.makeText(requireContext(), niceDate, Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("SimpleDateFormat")
    private fun timeFormat2(publishedAt: String) {
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
        Toast.makeText(requireContext(),ago,Toast.LENGTH_SHORT).show()

    }


}