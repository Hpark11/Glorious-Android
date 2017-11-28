package glorious.church.presbyterian.glorious.util

import android.text.TextUtils
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by hpark_ipl on 2017. 11. 28..
 */

data class Sermon(
        val id: String,
        val title: String,
        val description: String,
        val imagePath: String,
        val videoId: String
)

interface SermonAPI {
    @GET("/playlistItems?maxResults=50&part=snippet&playlistId={playlistId}&key={key}")
    fun sermonsList(@Path("playlistId") playlistId: String,
                    @Path("key") key: String): Observable<MutableList<Sermon>>

    @GET("/videos?part=snippet&id={id}&key={key}")
    fun featuredSermon(@Path("") id: String, @Path("key") key: String): Observable<Sermon>
}

object APIService {
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/youtube/v3")

        if (!TextUtils.isEmpty(key)) {
            val client = OkHttpClient.Builder()

                    .addInterceptor { chain ->
                        val request = chain.request()

                        val newReq = request.newBuilder()
                                .addHeader("", "")
                                .build()

                        chain.proceed(newReq)
                    }
                    .build()

            builder.client(client)
        }

        return builder.build().create(SermonAPI::class.java)
    }
}