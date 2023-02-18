package com.example.kabar.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kabar.R
import com.example.kabar.adapter.RecyclerAdapter
import com.example.kabar.databinding.BookmarkScreenBinding
import com.example.kabar.ui.viewmodel.BookmarkScreenViewModel
import com.example.kabar.ui.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkScreen : Fragment(R.layout.bookmark_screen) {
    private val binding: BookmarkScreenBinding by viewBinding()
    private val viewModel: BookmarkScreenViewModel by viewModels()
    private val itemViewModel: ItemViewModel by activityViewModels()
    private var adapter: RecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initArticleData()
        clickItem()
        backReturn()
    }





    private fun initArticleData() {
        adapter = RecyclerAdapter()
        viewModel.getAllArticles()
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            if (it != null && it.size >= 0) {
                adapter!!.differ.submitList(it)
                binding.bookmarkRecycler.adapter = adapter
                binding.bookmarkRecycler.layoutManager = LinearLayoutManager(requireContext())
            }
        })
    }




    private fun clickItem() {
        adapter!!.onItemClick = {
            val bundle = Bundle().apply {
                putParcelable("data", it)
            }
            findNavController().navigate(R.id.action_bookmarkScreen_to_infoItemScreen, bundle)
            itemViewModel.itemClick()
        }
    }



    private fun backReturn(){
        itemViewModel.returnClicked()
    }

}