package com.example.kabar.ui.screen

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kabar.R
import com.example.kabar.databinding.InfoItemScreenBinding
import com.example.kabar.model.Articles
import com.example.kabar.utils.TimeFormat.getTimeFormat
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class InfoItemScreen : Fragment(R.layout.info_item_screen) {
    private val binding: InfoItemScreenBinding by viewBinding()
    private var data:Articles? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()
        returnBack()
        clickLike()
        clickBookmark()
    }


    private fun loadData() {
        data = requireArguments().getParcelable("data")
        if (data != null) {
            binding.newsSource.text = data!!.source.name
            binding.newsHour.text = getTimeFormat(data!!.publishedAt)
            Glide.with(requireContext()).load(data!!.urlToImage).into(binding.headImg)
            binding.infoDesc.text = data!!.description
            binding.newsContent.text = trimContent(data!!.content)
        }
    }


    private fun returnBack() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun trimContent(content: String?):String{
        var trimContent = content
        return content?.substring(0,content.length - 14) ?: requireContext().getString(R.string.fake_content)

    }


    private fun clickLike(){
        binding.likeIc.setOnClickListener {
            if (!data!!.isLike){
                binding.likeIc.setImageResource(R.drawable.ic_like_active)
                data!!.isLike = true
            }
            else{
                binding.likeIc.setImageResource(R.drawable.ic_like_inactive)
                data!!.isLike = false
            }

            Toast.makeText(requireContext(),"${data!!.isLike}",Toast.LENGTH_SHORT).show()
        }

    }


    private fun clickBookmark(){
        binding.bookmarked.setOnClickListener {
            if (!data!!.isBookmarked){
                binding.bookmarked.setImageResource(R.drawable.bookmark_icon_active)
                data!!.isBookmarked = true
            }
            else{
                binding.bookmarked.setImageResource(R.drawable.bookmark_icon_inactive)
                data!!.isBookmarked = false
            }

            Toast.makeText(requireContext(),"${data!!.isBookmarked}",Toast.LENGTH_SHORT).show()

        }
    }









}