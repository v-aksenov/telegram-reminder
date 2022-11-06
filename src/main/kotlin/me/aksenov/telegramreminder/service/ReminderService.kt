package me.aksenov.telegramreminder.service

import me.aksenov.telegramreminder.logger.Logger
import me.aksenov.telegramreminder.model.Reminder
import me.aksenov.telegramreminder.storage.ReminderRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class ReminderService(private val reminderRepository: ReminderRepository) : Logger {

    fun saveReminder(reminder: Reminder): Reminder {
        return reminderRepository.save(
            reminder.copy(
                timeToReminder = getTimeToReminder(reminder.minutes, reminder.hours)
            )
        )
    }

    private fun getTimeToReminder(minutes: Long, hours: Long): Instant =
        if (minutes == 0L && hours == 0L) {
            throw IllegalArgumentException()
        } else {
            Instant.now()
                .plus(minutes, ChronoUnit.MINUTES)
                .plus(hours, ChronoUnit.HOURS)
        }

    fun removeReminder(id: ObjectId) {
        reminderRepository.deleteById(id)
    }

    fun getReminders(chatId: Long?): List<Reminder> =
        if (chatId == null) {
            reminderRepository.findByProcessedIsFalse()
        } else {
            reminderRepository.findByChatIdAndProcessedIsFalse(chatId)
        }
}
