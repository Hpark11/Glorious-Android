package glorious.church.presbyterian.glorious.util

import glorious.church.presbyterian.glorious.model.Result
import glorious.church.presbyterian.glorious.model.Sermon
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SermonAPI {
    @GET("playlistItems?")
    fun sermonsList(@Query("playlistId") playlistId: String,
                    @Query("maxResults") maxResults: Int = 50,
                    @Query("part") part: String = "snippet",
                    @Query("key") key: String = Factory.key
    ): Flowable<Result>

    @GET("videos?part=snippet&id={id}&key={key}")
    fun featuredSermon(@Path("") id: String, @Path("key") key: String): Observable<Sermon>

    companion object Factory {
        val sermonListId = "UU4Y-jkVQdsAAqSzD51chjJQ"
        val centerMessageListId = "UUwYStAqJNzgIEf33u7a0xiQ"

        // 62가지
        val messageId62 = "XOWZHSgo2cE"
        // 강단
        val messageIdMain = "eqpjAUrxAIw"
        // 1분
        val messageId1Min = "tLVsSZCuanI"
        // 3분
        val messageId3Min = "ADI7UI87BK8"
        // 5분
        val messageId5Min = "EjVYo15-3QU"

        val key: String = "AIzaSyB9WUuZbdt0h2zhhH3Y_PRmJ6zbUA1LWe0"

        fun createAPIService(): SermonAPI {
            val builder = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .build()

            return builder.create(SermonAPI::class.java)
        }
    }
}

