package com.example.kabar.ui.screen

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
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
import com.example.kabar.utils.checkOnline
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    private val binding: HomeScreenBinding by viewBinding()
    private var pagerAdapter: PagerAdapter? = null
    private val viewModel: HomeViewModel by viewModels()
    private val itemViewModel: ItemViewModel by activityViewModels()
    private var trendNews: Articles? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadData() {
        if (checkOnline(requireContext())) {
            initPagerAdapter()
            syncPagerTab()
            searchNews()
            transitionLatestNews()
            transitionTrendingNews()
            trendingNews()
            clickTrendNews()
            backHome()
        } else {
            Snackbar.make(binding.root, "please check your internet", Snackbar.LENGTH_SHORT).show()
        }
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
            is KabarResult.Error ->  Snackbar.make(binding.root,"${value.message}",Snackbar.LENGTH_SHORT).show()

        }
    }

    private fun bindingTrendNews(list: List<Articles>) {
        binding.spinKit.visibility = View.GONE
        if(list.isNotEmpty()){
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

        }
        else{
            Snackbar.make(binding.root,"there aren't data",Snackbar.LENGTH_SHORT).show()
        }

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


    private fun searchNews() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true


            override fun onQueryTextChange(newText: String?): Boolean {
                searchWordEverything(newText)
                return true
            }
        })

    }


    private fun backHome() {
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


    private fun searchWordEverything(searchWord: String?) {
        spinShow()
        if (searchWord != null && searchWord.isNotBlank()) {
            viewModel.getSearchNews(searchWord)
            viewModel.searchNewsLiveData.observe(viewLifecycleOwner, Observer {
                showSearchData(it)
            })
        }else {
            spinClose()
            Snackbar.make(binding.root,"${"Don't enter empty text!"}",Snackbar.LENGTH_SHORT).show()
        }


    }

    private fun showSearchData(data: KabarResult<NewsResponse>) {
        when (data) {
            is KabarResult.Success -> bindingTrendNews(data.data!!.articles)
            is KabarResult.Loading -> spinShow()
            is KabarResult.Error -> {
                spinClose()
                Snackbar.make(binding.root,"${data.message}",Snackbar.LENGTH_SHORT).show()
            }
        }
    }




    private fun spinShow() {
        binding.spinKit.visibility = View.VISIBLE
    }

    private fun spinClose() {
        binding.spinKit.visibility = View.GONE

    }


}


