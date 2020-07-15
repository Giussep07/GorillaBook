package com.giussepr.gorillabook.webService

import com.giussepr.gorillabook.dto.feed.FeedResDTO
import com.giussepr.gorillabook.utility.Constants.GorillaBookApi.GET_FEED
import retrofit2.Response
import retrofit2.http.GET

interface GorillaBookApi {

    @GET(GET_FEED)
    suspend fun getFeed(): Response<List<FeedResDTO>>
}