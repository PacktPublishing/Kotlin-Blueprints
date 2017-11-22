package com.kotlinblueprint.besocial.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinblueprint.besocial.R
import com.kotlinblueprint.besocial.extensions.inflate
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import kotlinx.android.synthetic.main.fragment_twitter.*


/**
 * A simple [Fragment] subclass.
 * Use the [TwitterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
abstract class TwitterFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Inflate the layout for this fragment
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            container.inflate(R.layout.fragment_twitter)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        loadTimeline()
        composeTweet.setOnClickListener {
            val builder = TweetComposer.Builder(activity)
                    .text(getString(R.string.tweet_text))
            builder.show()
        }
    }

    /*override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            container.inflate(R.layout.fragment_twitter, false)*/
    abstract fun loadTimeline()
}
