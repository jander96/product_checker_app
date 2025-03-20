package com.devj.todoproducts.core.presenter

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toSimpleDate(): String {
    val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("es"))

    val date = ZonedDateTime.parse(this, inputFormatter)
    return date.format(outputFormatter)
}