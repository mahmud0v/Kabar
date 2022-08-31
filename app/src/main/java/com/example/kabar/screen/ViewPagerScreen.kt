package com.example.kabar.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.databinding.IntroScreenItemBinding
import com.example.kabar.utils.LoadPagerData

class ViewPagerScreen : Fragment(R.layout.intro_screen_item) {

    private val binding: IntroScreenItemBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = LoadPagerData.getPagerData()
        val position = requireArguments().getInt("key")
        binding.introImg.setImageResource(list[position].imageRes)
        binding.intro1HeadTxt.text = getString(list[position].headText)
        binding.intro1DescTxt.text = getString(list[position].descText)
    }

}