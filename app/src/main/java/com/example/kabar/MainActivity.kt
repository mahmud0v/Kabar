package com.example.kabar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kabar.databinding.ActivityMainBinding
import com.example.kabar.ui.viewmodel.ItemViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment

        viewModel.itemLivedata.observe(this, observer)


        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)

        binding.bottomNavigation.setOnItemReselectedListener {

        }

    }


    private fun showBottomNavView() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavView() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private val observer = Observer<Boolean> {
        if (it) {
            hideBottomNavView()
        } else {
            showBottomNavView()
        }
    }


}