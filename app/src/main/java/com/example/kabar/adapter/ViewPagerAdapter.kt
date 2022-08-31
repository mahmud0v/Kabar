package com.example.kabar.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kabar.screen.ViewPagerScreen
import com.example.kabar.utils.Constants.Companion.VIEW_PAGER_ITEMS

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount() = VIEW_PAGER_ITEMS

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerScreen()
        fragment.arguments = Bundle().apply {
            putInt("key", position)
        }

        return fragment
    }


}