package com.kotlinblueprint.besocial.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.kotlinblueprint.besocial.R
import com.kotlinblueprint.besocial.util.TWEET_ID
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.TweetUtils
import com.twitter.sdk.android.tweetui.TweetView
import kotlinx.android.synthetic.main.activity_tweet_detail.*
import com.twitter.sdk.android.core.TwitterException


class TweetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        TweetUtils.loadTweet(intent.getLongExtra(TWEET_ID, Long.MIN_VALUE), object : Callback<Tweet>() {
            override fun success(result: Result<Tweet>) {
                llTweets.addView(TweetView(this@TweetDetailActivity, result.data))
            }

            override fun failure(exception: TwitterException) {
                // Toast.makeText(...).show();
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}
