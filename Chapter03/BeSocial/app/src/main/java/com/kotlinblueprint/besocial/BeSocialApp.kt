package com.kotlinblueprint.besocial

import android.app.Application
import android.content.Context
import android.util.Log
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType


/**
 * Created by hardik.trivedi on 06/09/17.
 */
class BeSocialApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                        getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build()
        Twitter.initialize(config)
        Twitter.initialize(this)
        initImageLoader()
    }

    private fun initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        val config = ImageLoaderConfiguration.Builder(this)
        config.apply {
            threadPriority(Thread.NORM_PRIORITY - 2)
            denyCacheImageMultipleSizesInMemory()
            diskCacheFileNameGenerator(Md5FileNameGenerator())
            diskCacheSize(50 * 1024 * 1024) // 50 MiB
            tasksProcessingOrder(QueueProcessingType.LIFO)
            writeDebugLogs() // Remove for release app

            // Initialize ImageLoader with configuration.
            ImageLoader.getInstance().init(config.build())
        }
    }
}