package com.norbertotaveras.android_nova.extensions

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

inline fun String.displayDateWith(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timezone: TimeZone = TimeZone.getDefault()
): String {
    return SimpleDateFormat(pattern, locale).apply { timeZone = timezone }.format(this)
}

inline fun String.displayDateWith(
    inputPattern: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    outputPattern: String = "MMM d, yyyy h:mm a",
    inputLocale: Locale = Locale.ROOT,
    outputLocale: Locale = Locale.getDefault(),
    timezone: TimeZone = TimeZone.getDefault()
): String? = runCatching {
    SimpleDateFormat(inputPattern, inputLocale).apply { timeZone = TimeZone.getTimeZone("UTC") }
        .parse(this)?.let {
            SimpleDateFormat(outputPattern, outputLocale).apply { timeZone = timezone }.format(it)
        }
}.getOrDefault(this)

inline fun String.toDayOfWeek(format: String = "yyyy-MM-dd"): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    val date = dateFormat.parse(this) // Parses the date string
    val calendar = Calendar.getInstance().apply {
        if (date != null) {
            time = date
        }
    }
    return SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
}