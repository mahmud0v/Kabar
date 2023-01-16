package com.example.kabar.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.ExploreTopicAdapter
import com.example.kabar.adapter.TrendingRecyclerAdapter
import com.example.kabar.databinding.ExploreScreenBinding
import com.example.kabar.model.ExploreTopic
import com.example.kabar.model.ExploreTopicEntity
import com.example.kabar.model.NewsResponse
import com.example.kabar.ui.viewmodel.ExploreScreenViewModel
import com.example.kabar.ui.viewmodel.ItemViewModel
import com.example.kabar.utils.KabarResult
import com.example.kabar.utils.SelectableTopicsData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreScreen : Fragment(R.layout.explore_screen) {
    private val binding: ExploreScreenBinding by viewBinding()
    private val viewModel: ExploreScreenViewModel by viewModels()
    private var exploreAdapter: ExploreTopicAdapter? = null
    private var trendNewsAdapter: TrendingRecyclerAdapter? = null
    private val itemViewModel: ItemViewModel by activityViewModels()
    private lateinit var newTopicList: List<ExploreTopic>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initExploreTopic()
        exploreTopicClick()
        navigateAllTopics()
        backExplore()
        clickTrendItem()

    }


    private fun initExploreTopic() {
        exploreAdapter = ExploreTopicAdapter()
        viewModel.getAllTopic()
        newTopicList = listOf<ExploreTopic>()
        viewModel.topicsLiveData.observe(viewLifecycleOwner, Observer {
            newTopicList = loadList(it)
            Toast.makeText(requireContext(), "${newTopicList.size}", Toast.LENGTH_SHORT).show()
            exploreAdapter!!.differ.submitList(randomList(newTopicList))
            binding.expTopicRv.adapter = exploreAdapter
            binding.expTopicRv.layoutManager = LinearLayoutManager(requireContext())
            loadTrend()
        })


    }

    private fun loadTrend(){
        loadTrendNews()
        clickTrendItem()
    }


    private fun clickTrendItem(){
        trendNewsAdapter?.onItemClick = { article ->
           val bundle = Bundle().apply {
               putParcelable("data",article)
           }
            findNavController().navigate(R.id.action_exploreScreen_to_infoItemScreen,bundle)
            itemViewModel.itemClick()
        }
    }


    private fun exploreTopicClick() {
        exploreAdapter?.onItemClick = { exploreTopic ->
            viewModel.getTopic(requireContext().getString(exploreTopic.title))
            viewModel.exploreLiveData.observe(viewLifecycleOwner, Observer {
                observer(exploreTopic, it)
            })
        }

    }


    private fun observer(exploreTopic: ExploreTopic, exploreTopicEntity: ExploreTopicEntity?) {
        val title = requireContext().getString(exploreTopic.title)
        if (exploreTopicEntity == null) {
            if (exploreTopic.click) {
                viewModel.insertTopic(ExploreTopicEntity(title, true))
            } else {
                viewModel.insertTopic(ExploreTopicEntity(title, false))
            }
        } else {
            if (exploreTopic.click) {
                viewModel.updateTopic(ExploreTopicEntity(title, true))
            } else {
                viewModel.updateTopic(ExploreTopicEntity(title, false))
            }
        }
    }


    private fun loadList(list: List<ExploreTopicEntity>?): List<ExploreTopic> {
        val oldList = SelectableTopicsData.getExploreTopics().shuffled()
        val newList = ArrayList<ExploreTopic>()
        if (list == null || list.isEmpty()) {
            return oldList
        } else {
            for (i in oldList.indices) {
                val title = requireContext().getString(oldList[i].title)
                var saved = false
                for (j in list.indices) {
                    if (title == list[j].title) {
                        saved = list[j].saved
                    }
                }
                newList.add(
                    ExploreTopic(
                        oldList[i].imgRes,
                        oldList[i].category,
                        oldList[i].title,
                        oldList[i].desc,
                        saved
                    )
                )
            }
            return newList
        }
    }

    private fun loadTrendNews() {
        trendNewsAdapter = TrendingRecyclerAdapter()
        Toast.makeText(requireContext(), "${newTopicList?.size}", Toast.LENGTH_SHORT).show()
        val exploreTopic = newTopicList.random()
        viewModel.trendsNews(requireContext().getString(exploreTopic.category))
        viewModel.trendNewsLiveData.observe(viewLifecycleOwner, observer)
        binding.popularTopicRv.adapter = trendNewsAdapter
        binding.popularTopicRv.layoutManager = LinearLayoutManager(requireContext())
    }


    private val observer = Observer<KabarResult<NewsResponse>> {
        when (it) {
            is KabarResult.Loading -> showLoader()
            is KabarResult.Success -> initTrendAdapter(it.data!!)
            is KabarResult.Error -> Toast.makeText(
                requireContext(),
                "${it.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initTrendAdapter(newsResponse: NewsResponse) {
        hideLoader()
        trendNewsAdapter!!.differ.submitList(newsResponse.articles)
    }

    private fun hideLoader() {
        binding.spinKit.visibility = View.GONE
    }


    private fun showLoader() {
        binding.spinKit.visibility = View.VISIBLE
    }

    private fun randomList(list: List<ExploreTopic>): List<ExploreTopic> {
        val randomList = ArrayList<ExploreTopic>()
        for (i in 0..2) {
            randomList.add(list[i])
        }
        return randomList
    }


    private fun navigateAllTopics() {
        binding.seeAllId.setOnClickListener {
            findNavController().navigate(R.id.action_exploreScreen_to_allTopicsScreen)
            itemViewModel.itemClick()
        }
    }

    private fun backExplore() {
        itemViewModel.returnClicked()
    }

}