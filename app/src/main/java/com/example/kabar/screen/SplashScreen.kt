package com.example.kabar.screen

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kabar.R
import com.example.kabar.utils.Constants.Companion.COUNT_DOWN_INTERVAL
import com.example.kabar.utils.Constants.Companion.MILLIS_IN_FUTURE

class SplashScreen : Fragment(R.layout.splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countDownTimer.start()

    }

    private val countDownTimer = object : CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) {
        override fun onTick(p0: Long) {

        }

        override fun onFinish() {
            val sharedPref =
                requireActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
            val currentItem = sharedPref.getInt("SharedPref", -1)
            if (currentItem == 2) {
              findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_homeViewPager)
            }
        }

    }
}