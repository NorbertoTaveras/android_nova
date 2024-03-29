package com.norbertotaveras.android_nova.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toLocalDateTime(): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}
fun Int.toLocalDateTime(): String {
    val date = Date(this.toLong() * 1000)
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}

fun Int.toLocalTime(): String {
    val date = Date(this * 1000L)
    val format = SimpleDateFormat("h:mm a z", Locale.getDefault())
    format.timeZone = TimeZone.getDefault()
    return format.format(date)
}