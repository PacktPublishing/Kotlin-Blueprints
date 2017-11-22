package com.kotlinblueprint.besocial.data.remote

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hardik.trivedi on 16/09/17.
 */
class MyTwitterApiClient(session: TwitterSession) : TwitterApiClient(session) {


    fun getTimelineService(): TimelineService = getService(TimelineService::class.java)

    interface TimelineService {
        @GET("/1.1/users/show.json")
        fun getUserDetails(@Query("user_id") id: Long): Call<User>

        @GET("/1.1/statuses/home_timeline.json")
        fun showHomeTimeline(@Query("count") count: Int = 200): Call<List<Tweet>>
    }
}