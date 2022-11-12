package me.aksenov.telegramreminder.service.model

data class CreateReminderRequest(
    val chatId: Long,
    val description: String,
    val minutes: Long,
    val hours: Long
) {

    fun toReminder(): Reminder = Reminder(
        chatId = chatId,
        description = description,
        minutes = minutes,
        hours = hours
    )
}
