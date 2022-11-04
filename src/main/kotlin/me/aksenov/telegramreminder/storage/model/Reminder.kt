package me.aksenov.telegramreminder.storage.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document
data class Reminder(
    @Id
    val id: ObjectId = ObjectId.get(),
    @Field
    var processed: Boolean = false,
    @Field
    val dateToReminder: Date,
    @Field
    val chatId: Long,
    @Field
    val description: String
)
