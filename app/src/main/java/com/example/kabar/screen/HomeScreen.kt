package com.example.kabar.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.PagerAdapter
import com.example.kabar.adapter.RecyclerAdapter
import com.example.kabar.databinding.HomeScreenBinding
import com.example.kabar.utils.SelectableTopicsData
import com.google.android.material.tabs.TabLayoutMediator
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeScreen : Fragment(R.layout.home_screen) {
    private val binding: HomeScreenBinding by viewBinding()
    private lateinit var pagerAdapter: PagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPagerAdapter()
        syncPagerTab()

//     timeFormat2("2022-09-05T09:14:55Z")
    }









    private fun initRecycler(){
        val adapter = RecyclerAdapter()
    }


    private fun initPagerAdapter(){
        pagerAdapter = PagerAdapter(this)
        binding.viewPager2.adapter = pagerAdapter
    }


    private fun syncPagerTab(){
        val data = SelectableTopicsData.getTopics()
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){tab,position ->
            tab.text = data[position]
        }.attach()
    }




}