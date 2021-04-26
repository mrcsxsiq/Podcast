package dev.marcos.podcast

import dev.marcos.podcast.data.Home
import retrofit2.Call
import retrofit2.http.GET

interface PodcastService {

    @GET("home.json")
    fun get(): Call<Home>
}