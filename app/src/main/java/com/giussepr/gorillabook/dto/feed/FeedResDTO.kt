package com.giussepr.gorillabook.dto.feed

import com.giussepr.gorillabook.entity.feed.Feed
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedResDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "post_body") val postBody: String,
    @Json(name = "unix_timestamp") val unixTimeStamp: String,
    @Json(name = "image") val imageUrl: String = ""
) {

    private fun toFeedEntity(): Feed {
        return Feed(
            id, firstName, lastName, postBody, unixTimeStamp, imageUrl
        )
    }

    companion object {
        fun toListFeedEntity(listFeedResDTO: List<FeedResDTO>): List<Feed> {
            val mutableList: MutableList<Feed> = arrayListOf()

            listFeedResDTO.forEach {
                mutableList.add(it.toFeedEntity())
            }

            return mutableList.toList()

        }
    }
}