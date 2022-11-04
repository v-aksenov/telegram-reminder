package me.aksenov.telegramreminder.storage.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Timestamp

@Document
data class Reminder(
    @Id
    val id: ObjectId = ObjectId.get(),
    var processed: Boolean = false,
    val timestampToReminder: Timestamp,
    val chatId: Long,
    val description: String
)
