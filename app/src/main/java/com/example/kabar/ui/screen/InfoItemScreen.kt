package com.example.kabar.ui.screen

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kabar.R
import com.example.kabar.databinding.InfoItemScreenBinding
import com.example.kabar.model.Articles
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class InfoItemScreen : Fragment(R.layout.info_item_screen) {
    private val binding: InfoItemScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()
        returnBack()
    }


    private fun loadData() {
        val data: Articles? = requireArguments().getParcelable("key")
        if (data != null) {
            binding.newsSource.text = data.source.name
            binding.newsHour.text = timeFormat(data.publishedAt)
            Glide.with(requireContext()).load(data.urlToImage).into(binding.headImg)
            binding.infoDesc.text = data.description
            binding.newsContent.text = data.content
        }
    }


    private fun returnBack() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }


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


}