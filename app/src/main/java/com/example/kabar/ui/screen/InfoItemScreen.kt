package com.example.kabar.ui.screen

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kabar.R
import com.example.kabar.databinding.InfoItemScreenBinding
import com.example.kabar.model.Articles
import com.example.kabar.ui.viewmodel.HomeViewModel
import com.example.kabar.ui.viewmodel.ItemViewModel
import com.example.kabar.utils.TimeFormat.getTimeFormat
import dagger.hilt.android.AndroidEntryPoint
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class InfoItemScreen : Fragment(R.layout.info_item_screen) {
    private val binding: InfoItemScreenBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()
    private var data: Articles? = null

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

        checkLike(data!!.isLike)
        checkBookmark(data!!.isBookmarked)
    }


    private fun returnBack() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun trimContent(content: String?): String {
        var trimContent = content
        return content?.substring(0, content.length - 14)
            ?: requireContext().getString(R.string.fake_content)

    }


    private fun checkLike(clicked: Boolean) {
        if (clicked) {
            binding.likeIc.setImageResource(R.drawable.ic_like_active)
        } else {
            binding.likeIc.setImageResource(R.drawable.ic_like_inactive)
        }


    }

    private fun clickLike() {
        binding.likeIc.setOnClickListener {
            if (!data!!.isLike) {
                binding.likeIc.setImageResource(R.drawable.ic_like_active)
                data!!.isLike = true
            } else {
                binding.likeIc.setImageResource(R.drawable.ic_like_inactive)
                data!!.isLike = false
            }
            viewModel.updateNews(data!!)
        }

    }

    private fun clickBookmark() {
        binding.bookmarked.setOnClickListener {
            if (!data!!.isBookmarked) {
                binding.bookmarked.setImageResource(R.drawable.bookmark_icon_active)
                data!!.isBookmarked = true
                viewModel.insertNews(data!!)
            } else {
                binding.bookmarked.setImageResource(R.drawable.bookmark_icon_inactive)
                viewModel.deleteNews(data!!.id)
                data!!.isBookmarked = false

            }

//            Toast.makeText(requireContext(),"${data!!.isBookmarked}",Toast.LENGTH_SHORT).show()

        }
    }


    private fun checkBookmark(bookmarked: Boolean) {
        if (bookmarked) {
            binding.bookmarked.setImageResource(R.drawable.bookmark_icon_active)
        } else {
            binding.bookmarked.setImageResource(R.drawable.bookmark_icon_inactive)
        }

    }


    private val observer = Observer<List<Articles>> {
        Toast.makeText(requireContext(), "${it.size}", Toast.LENGTH_SHORT).show()
    }


}