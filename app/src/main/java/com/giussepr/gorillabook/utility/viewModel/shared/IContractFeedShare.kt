package com.giussepr.gorillabook.utility.viewModel.shared

import com.giussepr.gorillabook.entity.feed.Feed

interface IContractFeedShare {

    interface ViewModel {
        fun addPost(feed: Feed)
        fun resetNewPost()
    }
}