package me.aksenov.telegramreminder.scheduler

import me.aksenov.telegramreminder.bot.ReminderBotService
import me.aksenov.telegramreminder.logger.Logger
import me.aksenov.telegramreminder.service.ReminderRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ReminderScheduler(
    private val reminderRepository: ReminderRepository,
    private val reminderBotService: ReminderBotService
) : Logger {

    @Scheduled(cron = "0 * * * * *")
    fun checkReminders() {
        val reminders = reminderRepository.findByTimeToReminderBeforeAndProcessedIsFalse(Instant.now())
        reminders
            .also { log.info("remind for ${it.size} tasks") }
            .forEach {
                reminderBotService.sendMessage(it.description, it.chatId)
                it.processed = true
                reminderRepository.save(it)
            }
    }
}
