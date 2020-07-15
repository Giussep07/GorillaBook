package com.giussepr.gorillabook.entity.feed

import android.net.Uri


data class Feed(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val postBody: String,
    val unixTimeStamp: String,
    val imageUrl: String,
    val imageUri: Uri? = null
)