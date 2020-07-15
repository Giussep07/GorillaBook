package com.giussepr.gorillabook.ui.feed.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("postDate")
fun bindPostDate(textView: TextView, timeStamp: String) {

    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    textView.text = sdf.format(Date(timeStamp.toLong() * 1000))

}

@BindingAdapter("webImageUrl", "imageUri")
fun bindWebImageUrl(imageView: ImageView, imageUrl: String, @Nullable imageUri: Uri?) {

    when {
        imageUrl.isNotEmpty() -> {
            Glide.with(imageView)
                .load(imageUrl)
                .into(imageView)
        }
        imageUri != null -> {
            Glide.with(imageView)
                .load(imageUri)
                .into(imageView)
        }
        else -> {
            imageView.visibility = View.GONE
        }
    }
}