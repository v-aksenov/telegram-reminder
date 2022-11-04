package me.aksenov.telegramreminder.service

import me.aksenov.telegramreminder.logger.Logger
import me.aksenov.telegramreminder.storage.ReminderRepository
import me.aksenov.telegramreminder.storage.model.Reminder
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class ReminderService(private val reminderRepository: ReminderRepository) : Logger{

    fun parseAndSaveReminder(chatId: Long, text: String) {
        parseTime(text)?.let {
            val reminder = Reminder(
                chatId = chatId,
                description = periodRegex.replaceFirst(text, ""),
                dateToReminder = it
            )
            reminderRepository.save(reminder)
            log.info("saved $reminder")
        }
    }

    private fun parseTime(text: String): Timestamp? {
        val days = dayRegex.find(text)?.groupValues?.firstOrNull()?.replace("d", "")?.toLong()
        val hours = hourRegex.find(text)?.groupValues?.firstOrNull()?.replace("h", "")?.toLong()
        val minutes = minuteRegex.find(text)?.groupValues?.firstOrNull()?.replace("m", "")?.toLong()
        val instant = Instant.now()
        days?.let { instant.plus(it, ChronoUnit.DAYS) }
        hours?.let { instant.plus(it, ChronoUnit.HOURS) }
        minutes?.let { instant.plus(it, ChronoUnit.MINUTES) }
        return Timestamp.from(Instant.now()).takeIf { days != null || hours != null || minutes != null }
    }
}

private val hourRegex: Regex = Regex("\\d+h")
private val minuteRegex: Regex = Regex("\\d+m")
private val dayRegex: Regex = Regex("\\d+d")
private val periodRegex: Regex = Regex("(\\d+d)? (\\d+h)? (\\d+m)?")
