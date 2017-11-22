package com.kotlinblueprint.besocial.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.kotlinblueprint.besocial.data.remote.MyTwitterApiClient
import com.kotlinblueprint.besocial.ui.activity.TweetDetailActivity
import com.kotlinblueprint.besocial.util.TWEET_ID
import com.kotlinblueprint.besocial.ui.adapter.TweetAdapter
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.fragment_twitter.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by hardik.trivedi on 18/09/17.
 */
class HomeTimeLineFragment : TwitterFragment() {
    override fun loadTimeline() {
        TwitterCore.getInstance().sessionManager.activeSession?.let {
            val timelineService = MyTwitterApiClient(TwitterCore.getInstance().sessionManager.activeSession)
                    .getTimelineService()
            timelineService.showHomeTimeline().enqueue(object : Callback<List<Tweet>>() {
                override fun success(result: Result<List<Tweet>>) {
                    rvTimeline.layoutManager = LinearLayoutManager(context)
                    rvTimeline.adapter = TweetAdapter(result.data) {
                        startActivity<TweetDetailActivity>(TWEET_ID to it.id)


                    }
                }

                override fun failure(exception: TwitterException) {
                    toast(exception.message.toString())
                }
            })
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TwitterFragment.
         */
        fun newInstance(): HomeTimeLineFragment = HomeTimeLineFragment()
    }
}