package dev.marcos.podcast.data

import com.google.gson.annotations.SerializedName
data class Region(

    @SerializedName("title")
    val title: String,

    @SerializedName("podcasts")
    val podcasts: List<Podcast>,

)
