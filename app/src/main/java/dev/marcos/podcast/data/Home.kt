package dev.marcos.podcast.data

import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("featured")
    val featured: List<Podcast>,

    @SerializedName("regions")
    val regions: List<Region>,
)
