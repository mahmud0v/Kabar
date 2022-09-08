package com.example.kabar.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.RecyclerAdapter
import com.example.kabar.databinding.LatestNewsRecyclerBinding
import com.example.kabar.utils.FakeList

class PagerScreen:Fragment(R.layout.latest_news_recycler) {
   private val binding:LatestNewsRecyclerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = RecyclerAdapter()
        adapter.differ.submitList(FakeList.getFakeList())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

}