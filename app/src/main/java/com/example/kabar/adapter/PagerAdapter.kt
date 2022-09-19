package com.example.kabar.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kabar.ui.screen.LatestPagerScreen
import com.example.kabar.utils.Constants.Companion.PAGER_SCREEN_COUNT

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = PAGER_SCREEN_COUNT

    override fun createFragment(position: Int): Fragment {
        val fragment = LatestPagerScreen()
        fragment.arguments = Bundle().apply {
            putInt("key", position)
        }
        return fragment
    }
}