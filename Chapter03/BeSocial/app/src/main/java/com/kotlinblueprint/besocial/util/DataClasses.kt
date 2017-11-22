package com.kotlinblueprint.besocial.util

/**
 * Created by hardik.trivedi on 21/09/17.
 */
data class LinkedInUser(val id: String
                        , val firstName: String
                        , val lastName: String
                        , val headline: String
                        , val siteStandardProfileRequest: StandardProfileRequest)

data class StandardProfileRequest(val url: String)

data class Time(val elapsedDays: Long, val elapsedHours: Long, val elapsedMinutes: Long, val elapsedSeconds: Long)