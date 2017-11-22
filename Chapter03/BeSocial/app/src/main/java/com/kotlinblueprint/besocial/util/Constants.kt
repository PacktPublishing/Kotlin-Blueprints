package com.kotlinblueprint.besocial.util

import com.linkedin.platform.utils.Scope

/**
 * Created by hardik.trivedi on 15/09/17.
 */
const val IS_CONNECTED_TO_SOCIAL_MEDIA = "is_connected_to_social_media"
const val IS_CONNECTED_TO_TWITTER = "is_connected_to_twitter"
const val IS_CONNECTED_TO_LINKEDIN = "is_connected_to_linkedin"

const val LINKEDIN_SUCCESS = 3672
const val TWITTER_SUCCESS = 140

fun buildScope(): Scope = Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS, Scope.W_SHARE)

const val TWEET_ID = "tweet_id"