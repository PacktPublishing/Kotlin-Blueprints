package com.kotlinblueprint.besocial.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.kotlinblueprint.besocial.R
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter
import com.twitter.sdk.android.tweetui.UserTimeline
import kotlinx.android.synthetic.main.fragment_twitter.*


/**
 * Created by hardik.trivedi on 18/09/17.
 */
class UserTimeLineFragment : TwitterFragment() {
    override fun loadTimeline() {
        TwitterCore.getInstance().sessionManager.activeSession?.let {
            val userTimeline = UserTimeline.Builder()
                    .screenName(TwitterCore.getInstance().sessionManager.activeSession.userName)
                    .build()
            val adapter = TweetTimelineRecyclerViewAdapter.Builder(context)
                    .setTimeline(userTimeline)
                    .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                    .build()
            rvTimeline.layoutManager = LinearLayoutManager(context)
            rvTimeline.adapter = adapter
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TwitterFragment.
         */
        fun newInstance(): UserTimeLineFragment = UserTimeLineFragment()
    }
}