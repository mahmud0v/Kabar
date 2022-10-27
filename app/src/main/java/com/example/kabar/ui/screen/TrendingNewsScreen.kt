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
import com.example.kabar.adapter.TrendingRecyclerAdapter
import com.example.kabar.databinding.TrendingNewsBinding
import com.example.kabar.model.Articles
import com.example.kabar.model.NewsResponse
import com.example.kabar.ui.viewmodel.HomeViewModel
import com.example.kabar.utils.KabarResult
import com.example.kabar.utils.SelectableTopicsData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingNewsScreen : Fragment(R.layout.trending_news) {
    private val binding: TrendingNewsBinding by viewBinding()
    private var adapter: TrendingRecyclerAdapter? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadTopic()
        initialRecycler()
        returnBack()
        clickEvent()
    }


    private fun initialRecycler() {
        adapter = TrendingRecyclerAdapter()
        binding.spinKit2.visibility = View.VISIBLE
        viewModel.trendNewsLiveData.observe(viewLifecycleOwner, observer)
        binding.trendingRecycler.adapter = adapter
        binding.trendingRecycler.layoutManager = LinearLayoutManager(requireContext())
    }


    private val observer = Observer<KabarResult<NewsResponse>> {
        when (it) {
            is KabarResult.Loading -> binding.spinKit2.visibility = View.VISIBLE
            is KabarResult.Success -> loadData(it.data!!.articles)
            is KabarResult.Error -> Toast.makeText(
                requireContext(),
                "${it.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadTopic() {
        val topicSize = SelectableTopicsData.getHeadlineCategories().size
        val random = (topicSize * Math.random()).toInt()
        val category = SelectableTopicsData.getHeadlineCategories()[random]
        viewModel.getTrendNews(category)

    }


    private fun loadData(list: List<Articles>) {
        binding.spinKit2.visibility = View.GONE
        adapter!!.differ.submitList(list)
    }

    private fun returnBack() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun clickEvent() {
        adapter?.onItemClick = { data ->
            val bundle = Bundle().apply {
                putParcelable("data", data)
            }
            findNavController().navigate(R.id.action_trendingNews_to_infoItemScreen,bundle)
        }
    }

}