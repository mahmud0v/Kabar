package com.example.kabar.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kabar.R
import com.example.kabar.adapter.PagerAdapter
import com.example.kabar.databinding.HomeScreenBinding
import com.example.kabar.model.Articles
import com.example.kabar.model.NewsResponse
import com.example.kabar.ui.viewmodel.HomeViewModel
import com.example.kabar.ui.viewmodel.ItemViewModel
import com.example.kabar.utils.KabarResult
import com.example.kabar.utils.SelectableTopicsData
import com.example.kabar.utils.TimeFormat.getTimeFormat
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    private val binding: HomeScreenBinding by viewBinding()
    private var pagerAdapter: PagerAdapter? = null
    private val viewModel: HomeViewModel by viewModels()
    private val itemViewModel: ItemViewModel by activityViewModels()
    private var trendNews: Articles? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPagerAdapter()
        syncPagerTab()
        transitionLatestNews()
        transitionTrendingNews()
        trendingNews()
        clickTrendNews()
        launchHome()
    }


    private fun initPagerAdapter() {
        pagerAdapter = PagerAdapter(this)
        binding.viewPager2.adapter = pagerAdapter
        binding.spinKit.visibility = View.GONE
    }


    private fun syncPagerTab() {
        val data = SelectableTopicsData.getTopics()
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = data[position]
        }.attach()
    }


    private fun trendingNews() {
        viewModel.getTrendNews("technology")
        viewModel.trendNewsLiveData.observe(viewLifecycleOwner, observer)
    }

    private val observer = Observer<KabarResult<NewsResponse>> { value ->
        when (value) {
            is KabarResult.Loading -> binding.spinKit.visibility = View.VISIBLE
            is KabarResult.Success -> bindingTrendNews(value.data!!.articles)
            is KabarResult.Error -> Toast.makeText(
                requireContext(),
                "${value.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun bindingTrendNews(list: List<Articles>): Articles {
        binding.spinKit.visibility = View.GONE
        val limit = list.size - 1
        trendNews = list[0]
        for (i in 0..limit) {
            if (list[i].urlToImage != null && list[i].url != null && list[i].description
                != null && list[i].author != null && list[i].title != null && list[i].publishedAt != null && list[i].content != null
            ) {
                trendNews = list[i]
                break
            }
        }
        Glide.with(binding.trendingInclude.root).load(trendNews!!.urlToImage)
            .into(binding.trendingInclude.trendImg)
        binding.trendingInclude.trendHeadTxt.text = trendNews!!.description
        binding.trendingInclude.trendNewsSource.text = trendNews!!.source.name
        binding.trendingInclude.trendHour.text = getTimeFormat(trendNews!!.publishedAt)
        return trendNews!!
    }

    private fun transitionLatestNews() {
        binding.latestSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_latestNewsScreen)
            itemViewModel.itemClick()
        }
    }

    private fun transitionTrendingNews() {
        binding.trendingSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_trendingNews)
            itemViewModel.itemClick()
        }
    }


    private fun launchHome() {
        itemViewModel.returnClicked()
    }


    private fun clickTrendNews() {
        binding.trendingInclude.trendingLayout.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("data", trendNews)
            }

            findNavController().navigate(R.id.action_homeScreen_to_infoItemScreen, bundle)
            itemViewModel.itemClick()
        }
    }


}