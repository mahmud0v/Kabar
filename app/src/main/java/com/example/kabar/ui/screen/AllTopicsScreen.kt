package com.example.kabar.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.ExploreTopicAdapter
import com.example.kabar.databinding.AllExploreScreenBinding
import com.example.kabar.model.ExploreTopic
import com.example.kabar.model.ExploreTopicEntity
import com.example.kabar.ui.viewmodel.ExploreScreenViewModel
import com.example.kabar.ui.viewmodel.ItemViewModel
import com.example.kabar.utils.SelectableTopicsData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTopicsScreen : Fragment(R.layout.all_explore_screen) {
    private val binding: AllExploreScreenBinding by viewBinding()
    private val viewModel: ExploreScreenViewModel by viewModels()
    private var adapter: ExploreTopicAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initExploreTopic()
        exploreTopicClick()
    }

    private fun initExploreTopic() {
        adapter = ExploreTopicAdapter()
        viewModel.getAllTopic()
        viewModel.topicsLiveData.observe(viewLifecycleOwner, Observer {
            adapter!!.differ.submitList(loadList(it))
            binding.topicsRv.adapter = adapter
            binding.topicsRv.layoutManager = LinearLayoutManager(requireContext())
        })

    }

    private fun exploreTopicClick() {
        adapter?.onItemClick = { exploreTopic ->
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
        val oldList = SelectableTopicsData.getExploreTopics()
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

}
