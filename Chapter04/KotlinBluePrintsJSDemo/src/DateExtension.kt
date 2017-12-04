/**
 * Created by hardik.trivedi on 29/10/17.
 */

fun Long.getShortDate(): String {
    val getFormattedDate: dynamic = js("window.getShortDate")
    return getFormattedDate(this)
}

fun Long.getFullDate(): String {
    val getFormattedDate: dynamic = js("window.getFullDate")
    return getFormattedDate(this)
}

fun Long.getFullWeekDay(): String {
    val getFormattedDate: dynamic = js("window.getFullWeekDay")
    return getFormattedDate(this)
}