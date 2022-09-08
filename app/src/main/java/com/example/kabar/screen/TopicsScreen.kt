package com.example.kabar.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.FlexboxAdapter
import com.example.kabar.databinding.SelectTopicsScreenBinding
import com.example.kabar.utils.SelectableTopicsData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class TopicsScreen : Fragment(R.layout.select_topics_screen) {


    private val binding: SelectTopicsScreenBinding by viewBinding()
    private lateinit var adapter: FlexboxAdapter
    private var condition = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FlexboxAdapter()
        initRV()
        setLayoutManager()
        directionNextPage()
        saveCondition()
    }


    private fun saveCondition() {
        val sharedPref = requireActivity().getSharedPreferences("topics", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("topics", condition).commit()
    }


    private fun initRV() {
        val topics = SelectableTopicsData.getTopics()
        adapter.differ.submitList(topics)
    }

    private fun setLayoutManager() {
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.flexBox.adapter = adapter
        binding.flexBox.layoutManager = layoutManager
    }

    private fun directionNextPage() {

        binding.topicsNextBtn.setOnClickListener {
            condition = true
            saveCondition()
            findNavController().navigate(R.id.action_topicsScreen_to_homeScreen)
        }
    }


}