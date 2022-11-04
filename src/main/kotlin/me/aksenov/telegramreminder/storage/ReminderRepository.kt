package me.aksenov.telegramreminder.storage

import me.aksenov.telegramreminder.storage.model.Reminder
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ReminderRepository : MongoRepository<Reminder, ObjectId> {

    fun findByDateToReminderBeforeAndProcessedIsFalse(date: Date): List<Reminder>
}
