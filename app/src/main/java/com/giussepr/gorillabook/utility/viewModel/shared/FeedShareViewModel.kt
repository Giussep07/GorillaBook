package com.giussepr.gorillabook.utility.viewModel.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giussepr.gorillabook.entity.feed.Feed

class FeedShareViewModel : ViewModel(), IContractFeedShare.ViewModel {

    private val _newPost = MutableLiveData<Feed>()
    val newPost: LiveData<Feed>
        get() = _newPost

    override fun addPost(feed: Feed) {
        _newPost.value = feed
    }

    override fun resetNewPost() {
        _newPost.value = null
    }
}