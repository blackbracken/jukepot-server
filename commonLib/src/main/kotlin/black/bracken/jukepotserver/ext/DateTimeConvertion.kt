package black.bracken.jukepotserver.ext

import org.joda.time.DateTimeZone

typealias JodaDateTime = org.joda.time.DateTime
typealias JavaDateTime = java.time.ZonedDateTime

fun JodaDateTime.toJavaDateTime(): JavaDateTime =
    JavaDateTime.of(
        year,
        monthOfYear,
        dayOfMonth,
        hourOfDay,
        minuteOfHour,
        secondOfMinute,
        0,
        java.time.ZoneId.systemDefault()
    )


fun JavaDateTime.toJodaDateTime(): JodaDateTime =
    JodaDateTime(year, monthValue, dayOfMonth, hour, minute, second, DateTimeZone.getDefault())