package me.aksenov.telegramreminder.utils

import me.aksenov.telegramreminder.service.model.Reminder

fun parseMessageToReminder(chatId: Long, message: String): Reminder {
    val minutes = message.getAmount(minuteRegex)
    val hours = message.getAmount(hourRegex)
    val description = message
        .replace(minuteRegex, "")
        .replace(hourRegex, "")
        .trim()
    return Reminder(
        minutes = minutes,
        hours = hours,
        chatId = chatId,
        description = description
    )
}

private fun String.getAmount(regex: Regex): Long =
    regex.find(this)
        ?.groupValues
        ?.firstOrNull()
        ?.replace(regex.pattern.last().toString(), "")
        ?.toLong()
        ?: 0

private val hourRegex: Regex = Regex("\\d+h")
private val minuteRegex: Regex = Regex("\\d+m")
