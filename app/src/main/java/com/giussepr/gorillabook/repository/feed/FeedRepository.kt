package com.giussepr.gorillabook.repository.feed

import com.giussepr.gorillabook.dto.feed.FeedResDTO
import com.giussepr.gorillabook.entity.feed.Feed
import com.giussepr.gorillabook.ui.feed.IContractFeed
import com.giussepr.gorillabook.webService.GorillaBookApi
import retrofit2.Response
import javax.inject.Inject

class FeedRepository @Inject constructor(private val gorillaBookApi: GorillaBookApi) :
    IContractFeed.Repository {

    override suspend fun getFeed(): Response<List<FeedResDTO>> {
        return gorillaBookApi.getFeed()
    }
}