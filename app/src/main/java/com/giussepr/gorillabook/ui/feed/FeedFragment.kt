package com.giussepr.gorillabook.ui.feed

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.giussepr.gorillabook.R
import com.giussepr.gorillabook.databinding.FeedFragmentBinding
import com.giussepr.gorillabook.databinding.FeedFragmentItemBinding
import com.giussepr.gorillabook.ui.feed.adapter.FeedAdapter
import com.giussepr.gorillabook.utility.viewModel.ViewModelFactory
import com.giussepr.gorillabook.utility.viewModel.shared.FeedShareViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FeedFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FeedFragmentBinding
    private val viewModel: FeedViewModel by viewModels { viewModelFactory }
    private val shareViewModel: FeedShareViewModel by activityViewModels()

    private lateinit var adapter: FeedAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.feed_fragment, container, false
        )

        binding.textViewNewPost.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToCreatePostFragment())
        }

        setupAdapter()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getFeed()

        viewModel.feed.observe(viewLifecycleOwner, Observer { feedList ->
            if (feedList.isNotEmpty()) {

                if (binding.viewSwitcher.nextView.id == binding.container.id) {
                    binding.viewSwitcher.showNext()
                }

                adapter.submitList(feedList)
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shareViewModel.newPost.observe(requireActivity(), Observer { newFeed ->
            if (newFeed != null) {
                viewModel.addFeed(newFeed)
            }
        })
    }

    private fun setupAdapter() {
        adapter = FeedAdapter()
        binding.recyclerView.adapter = adapter
    }

}