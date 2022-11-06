package me.aksenov.telegramreminder.storage

import me.aksenov.telegramreminder.model.Reminder
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.Instant

interface ReminderRepository : MongoRepository<Reminder, ObjectId> {

    fun findByTimeToReminderBeforeAndProcessedIsFalse(timeToReminder: Instant): List<Reminder>

    fun findByChatIdAndProcessedIsFalse(chatId: Long): List<Reminder>

    fun findByProcessedIsFalse(): List<Reminder>
}
