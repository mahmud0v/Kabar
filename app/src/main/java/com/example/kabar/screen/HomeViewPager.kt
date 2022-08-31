package com.example.kabar.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.ViewPagerAdapter
import com.example.kabar.databinding.Intro1ScreenBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPager : Fragment(R.layout.intro1_screen) {
    private val binding: Intro1ScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pagerAdapter = ViewPagerAdapter(this)
        binding.viewPager2Id.adapter = pagerAdapter
        binding.dotsIndicator.attachTo(binding.viewPager2Id)
        binding.nextBtn.setOnClickListener {
            val currentItem = binding.viewPager2Id.currentItem
            savePref(currentItem)
            if (currentItem == 2) {
                findNavController().navigate(R.id.action_homeViewPager_to_homeScreen)
            } else {
                binding.viewPager2Id.currentItem = currentItem+1
            }
        }

    }


    private fun showToast(currentItem:Int){
        Toast.makeText(requireContext(),"$currentItem",Toast.LENGTH_SHORT).show()
    }

    private fun savePref(currentItem:Int){
        val sharedPref = requireActivity().getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
        sharedPref.edit().putInt("SharedPref",currentItem).commit()
    }
}


