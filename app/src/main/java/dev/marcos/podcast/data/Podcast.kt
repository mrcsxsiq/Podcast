package dev.marcos.podcast.data

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("rss")
    val rss: String
)
