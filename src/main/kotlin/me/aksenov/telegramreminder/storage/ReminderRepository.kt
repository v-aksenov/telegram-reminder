package me.aksenov.telegramreminder.storage

import me.aksenov.telegramreminder.storage.model.Reminder
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.sql.Timestamp

interface ReminderRepository : MongoRepository<Reminder, ObjectId> {

    fun findByTimestampToReminderBeforeAndProcessedIsFalse(timestamp: Timestamp): List<Reminder>
}
