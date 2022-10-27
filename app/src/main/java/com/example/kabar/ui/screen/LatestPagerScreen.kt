package com.example.kabar.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.RecyclerAdapter
import com.example.kabar.databinding.LatestNewsRecyclerBinding
import com.example.kabar.model.Articles
import com.example.kabar.model.NewsResponse
import com.example.kabar.ui.viewmodel.NewsViewModel
import com.example.kabar.utils.KabarResult
import com.example.kabar.utils.SelectableTopicsData
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestPagerScreen : Fragment(R.layout.latest_news_recycler) {
    private val binding: LatestNewsRecyclerBinding by viewBinding()
    private var adapter: RecyclerAdapter? = null
    private val viewModel: NewsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        clickEvent()
    }


    private fun initRecycler() {
        binding.spinKit2.visibility = View.VISIBLE
        adapter = RecyclerAdapter()
        val position = requireArguments().getInt("key")
        val topics = SelectableTopicsData.getHeadlineCategories()
        viewModel.getTrendNews(topics[position])
        viewModel.trendNewsLiveData.observe(viewLifecycleOwner, observer)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun clickEvent() {
        val screen = requireArguments().getString("screen")
        adapter!!.onItemClick = { data ->
            val bundle = Bundle().apply {
                putParcelable("data", data)
            }

            if (screen == "home") {
                findNavController().navigate(R.id.action_homeScreen_to_infoItemScreen, bundle)
            } else {
                findNavController().navigate(R.id.action_latestNewsScreen_to_infoItemScreen, bundle)
            }


        }


    }

    private val observer = Observer<KabarResult<NewsResponse>> {
        when (it) {
            is KabarResult.Loading -> binding.spinKit2.visibility = View.VISIBLE
            is KabarResult.Success -> adapter!!.differ.submitList(list(it.data!!.articles))
            is KabarResult.Error -> Snackbar.make(
                binding.root,
                "${it.message}",
                Snackbar.LENGTH_SHORT
            ).show()

        }
    }

    private fun list(list: List<Articles>): List<Articles> {
        binding.spinKit2.visibility = View.GONE
        val newList = mutableListOf<Articles>()
        val limit = list.size - 1
        for (i in 0..limit) {
            newList.add(list[i])
        }

        return newList
    }

}