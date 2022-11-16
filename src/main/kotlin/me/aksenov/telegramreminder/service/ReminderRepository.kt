package me.aksenov.telegramreminder.service

import me.aksenov.telegramreminder.service.model.Reminder
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.Instant

interface ReminderRepository : MongoRepository<Reminder, ObjectId> {

    fun findByTimeToReminderBeforeAndProcessedIsFalse(timeToReminder: Instant): List<Reminder>

    fun findByChatIdAndProcessedIsFalse(chatId: Long): List<Reminder>
}
