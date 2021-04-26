package dev.marcos.podcast

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIService {
    private const val baseURL : String = "https://gist.githubusercontent.com/mrcsxsiq/cee65befe095fc878ec44f7e1d9e2d8e/raw/"
    private const val url = baseURL + "118690d1626619b1c5535779733a64e90533c4b5/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val podcastService: PodcastService = retrofit.create()
}