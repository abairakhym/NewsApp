package android.example.newsdemoapp.api

import retrofit2.http.GET

interface APIRequest {

    @GET("/v1/latest-news?language=en&apiKey=WX8dB4mV5gDyLh6-Xklcqzrb7-c0D5XwIT0fQrGvrBJUIPlv")
    suspend fun getNews() : NewsJSON
}