package com.giussepr.gorillabook.ui.feed

import com.giussepr.gorillabook.dto.feed.FeedResDTO
import com.giussepr.gorillabook.entity.feed.Feed
import retrofit2.Response

interface IContractFeed {

    interface ViewModel {
        fun getFeed()
        fun addFeed(feed: Feed)
    }

    interface Repository {
        suspend fun getFeed(): Response<List<FeedResDTO>>
    }
}