package com.example.kabar.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.PagerAdapter
import com.example.kabar.adapter.RecyclerAdapter
import com.example.kabar.databinding.LatestNewsBinding
import com.example.kabar.utils.SelectableTopicsData
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestNewsScreen : Fragment(R.layout.latest_news) {
    private val binding: LatestNewsBinding by viewBinding()
    private var pagerAdapter: PagerAdapter? = null
    private var adapter: RecyclerAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPagerAdapter()
        syncPagerTab()
        returnBack()
    }


    private fun initPagerAdapter() {
        pagerAdapter = PagerAdapter(this)
        adapter = RecyclerAdapter()
        binding.viewPager2.adapter = pagerAdapter
    }


    private fun syncPagerTab() {
        val data = SelectableTopicsData.getTopics()
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = data[position]
        }.attach()
    }

    private fun returnBack() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}