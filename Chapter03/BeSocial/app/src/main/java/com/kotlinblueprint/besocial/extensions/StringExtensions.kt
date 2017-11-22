package com.kotlinblueprint.besocial.extensions

import com.kotlinblueprint.besocial.util.Time
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by hardik.trivedi on 21/09/17.
 */
/*
fun String.getDateInHours(): String {
    */
/*val day = DateTime.Day(TimeUnit.SECONDS.toDays(this.toLong()))
    val hours = DateTime.Hour(TimeUnit.SECONDS.toHours(this.toLong()) - day.value * 24)
    val minute = DateTime.Minute(TimeUnit.SECONDS.toMinutes(this.toLong()) - TimeUnit.SECONDS.toHours(this.toLong()) * 60)
    val second = DateTime.Seconds(TimeUnit.SECONDS.toSeconds(this.toLong()) - TimeUnit.SECONDS.toMinutes(this.toLong()) * 60)*//*


    val format = SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH)
    format.isLenient = true

    val date = format.parse(this)
    val millis = date.time

    val day = TimeUnit.SECONDS.toDays(this.toLong())
    val hours = TimeUnit.SECONDS.toHours(this.toLong()) - day * 24
    val minute = TimeUnit.SECONDS.toMinutes(this.toLong()) - TimeUnit.SECONDS.toHours(this.toLong()) * 60
    val second = TimeUnit.SECONDS.toSeconds(this.toLong()) - TimeUnit.SECONDS.toMinutes(this.toLong()) * 60

    return when {
        day > 0 -> "$day d"
        hours > 0 -> "$hours h"
        minute > 0 -> "$minute m"
        else -> "$second s"
    }
}*/



fun String.getDateInHours(): String {
    val format = SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH)
    format.isLenient = true

    val date = format.parse(this)
    val today = Date()

    val (elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds) = getTimeDifference(fromDate = date, toDate = today)

    return when {
        elapsedDays > 0 -> "$elapsedDays d"
        elapsedHours > 0 -> "$elapsedHours h"
        elapsedMinutes > 0 -> "$elapsedMinutes m"
        else -> "$elapsedSeconds s"
    }
}

fun getTimeDifference(fromDate: Date, toDate: Date): Time {
    var different = toDate.time - fromDate.time


    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24

    val elapsedDays = different / daysInMilli
    different %= daysInMilli

    val elapsedHours = different / hoursInMilli
    different %= hoursInMilli

    val elapsedMinutes = different / minutesInMilli
    different %= minutesInMilli

    val elapsedSeconds = different / secondsInMilli

    return Time(elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds)
}


