package com.ajmr.tracker.ui

import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatAmountPe(amount: Double): String {
    val locale = Locale("es", "PE")
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(amount)
}

fun formatTransactionDate(timestamp: Long): String {
    val dateTime = timestamp.toLocalDateTime()
    val date = dateTime.toLocalDate()
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val timeFormatter = DateTimeFormatter.ofPattern("dd MMM")

    return when (date) {
        today -> "Hoy, ${dateTime.format(timeFormatter)}"
        yesterday -> "Ayer, ${dateTime.format(timeFormatter)}"
        else -> dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("es", "PE")))
    }
}

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}